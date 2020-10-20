package com.arkademy.ankasa.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityFormProfileBinding
import com.arkademy.ankasa.register.RegisterViewModel
import com.arkademy.ankasa.route.RouteApiService
import com.arkademy.ankasa.route.RouteModel
import com.arkademy.ankasa.route.RouteResponse
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import com.reginald.editspinner.EditSpinner
import kotlinx.android.synthetic.main.activity_form_profile.*
import kotlinx.android.synthetic.main.item_booking_ticket.view.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.create
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.create
import java.io.File
import java.text.FieldPosition

class FormProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormProfileBinding
    private lateinit var sharePreferencesHelper: PreferenceHelper
    private lateinit var viewModel: FormProfileViewModel
    private var img: MultipartBody.Part? = null


    private var selectedLoc = ""

    companion object {
        private val IMAGE_PICK_CODE = 1000
        val PERMISSION_CODE = 1001
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_profile)
        viewModel = ViewModelProvider(this).get(FormProfileViewModel::class.java)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_form_profile)
        sharePreferencesHelper = PreferenceHelper(this)
        viewModel.setSharedPreference(sharePreferencesHelper)

        val service = ApiClient.getApiClientToken(this)?.create(ProfileAPIService::class.java)
        val serviceRoute = ApiClient.getApiClientToken(this)?.create(RouteApiService::class.java)


        viewModel.setSharedPreference(sharePreferencesHelper)

        if (service != null) {
            viewModel.setProfileService(service)
        }

        if (serviceRoute != null) {
            viewModel.setRouteService(serviceRoute)
        }
        binding.tvChangeProfilePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, FormProfileActivity.PERMISSION_CODE)
                }
                else {
                    pickImgGallery()
                }
            } else {
                pickImgGallery()
            }
        }

//        Log.d("idUser", "$idUser")
//        val b = binding.etProfileUsername.text.toString()
//        val c = binding.etProfilePhone.text.toString()
//        val d = binding.etProfileAdress.text.toString()
//        val e = binding.etProfilePostCode.text.toString()
//        Log.d("selectedloc", "$selectedLoc")
//        Log.d("username", "$b")
//        Log.d("phone", "$c")
//        Log.d("addredd", "$d")
//        Log.d("postcode", "$e")
//
//        binding.btnSubmit.setOnClickListener {
//            viewModel.callPostProfile(
//                createPartFromString(idUser),
//                createPartFromString("5"),
//                createPartFromString("zakiz"),
//                createPartFromString("087777"),
//                createPartFromString("ponorogo"),
//                createPartFromString("98888"),
//                img
//                )
//
//        }

            viewModel.initSpinnerLoc()
            setUpView()
            setUpListener()
            subcribeLiveData()

    }

    private fun setUpView() {
        val register = sharePreferencesHelper.getBoolean(Constants.PREF_REGISTER)

        when (register) {
            true -> {
                binding.btnSave.visibility = View.GONE
                binding.btnSubmit.visibility = View.VISIBLE

            }
            else -> {
                binding.btnSave.visibility = View.VISIBLE
                binding.btnSubmit.visibility = View.GONE

            }
        }
    }

    private fun setUpListener(){
        val idUser = sharePreferencesHelper.getString(Constants.KEY_ID).toString()
        binding.btnSubmit.setOnClickListener {
            viewModel.callPostProfile(
                createPartFromString(idUser),
                createPartFromString(selectedLoc),
                createPartFromString(binding.etProfileUsername.text.toString()),
                createPartFromString(binding.etProfilePhone.text.toString()),
                createPartFromString(binding.etProfileAdress.text.toString()),
                createPartFromString(binding.etProfilePostCode.text.toString()),
                img
            )
        }
        binding.btnSave.setOnClickListener {
            viewModel.updateProfile(
                createPartFromString(idUser),
                createPartFromString(selectedLoc),
                createPartFromString(binding.etProfileUsername.text.toString()),
                createPartFromString(binding.etProfilePhone.text.toString()),
                createPartFromString(binding.etProfileAdress.text.toString()),
                createPartFromString(binding.etProfilePostCode.text.toString()),
                img
            )
        }
    }

    private fun subcribeLiveData() {
        val spinner = binding.etProfileCity  as Spinner
        viewModel.isResponseSpinner.observe(this, Observer {
            val listLoc = it.data?.map {
                RouteModel(
                    it.idRoutes,
                    it.city
                )
            } ?: listOf()
            val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listLoc.map {
                it.city
            })
            binding.etProfileCity.setAdapter(adapter)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) { }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedLoc = listLoc[position].id_route.toString()
                    Toast.makeText(this@FormProfileActivity, "$selectedLoc", Toast.LENGTH_SHORT).show()
                }
            }
        })
                viewModel.isUpdateProfile.observe(this, Observer {
                    if (it) {
                        Toast.makeText(this, "Data Update!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Update Failed!", Toast.LENGTH_SHORT).show()
                    }
                })
                viewModel.isFormProfile.observe(this, Observer {
                    if (it) {
                        Toast.makeText(this, "Data Sent!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun pickImgGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, FormProfileActivity.IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            FormProfileActivity.PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED) {
                    pickImgGallery()
                }
                else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == FormProfileActivity.IMAGE_PICK_CODE) {
            binding.etImage.setImageURI(data?.data)
            val filePath = getPath(this, data?.data)
            val file = File(filePath)

            val mediaTypeImg = "image/jpeg".toMediaType()
            val inputStream = contentResolver.openInputStream(data?.data!!)
            val reqFile: RequestBody? = inputStream?.readBytes()?.toRequestBody(mediaTypeImg)
            img = reqFile?.let { it1 ->
                MultipartBody.Part.createFormData("image", file.name,
                    it1
                )
            }
        }
    }

    fun getPath(context: Context, uri: Uri?): String {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? =
            uri?.let { context.contentResolver.query(it, proj, null, null, null) }

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }

        if (result == null) {
            result = "Not found"
        }
        return result
    }

    @NonNull
    private fun createPartFromString(json: String): RequestBody {
        val mediaType = "multipart/form-data".toMediaType()
        return json
            .toRequestBody(mediaType)
    }
}