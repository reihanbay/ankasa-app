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
import com.arkademy.ankasa.forgot.ForgotPassActivity
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
    private lateinit var sharedpref: PreferenceHelper
    private lateinit var viewModel: FormProfileViewModel

    private var img: MultipartBody.Part? = null
    private var selectedLoc = ""

    companion object {
        private val IMAGE_PICK_CODE = 1000
        val PERMISSION_CODE = 1001
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form_profile)
        sharedpref = PreferenceHelper(applicationContext)

        val service = ApiClient.getApiClientToken(this)?.create(ProfileAPIService::class.java)
        val serviceRoute = ApiClient.getApiClientToken(this)?.create(RouteApiService::class.java)

        viewModel = ViewModelProvider(this).get(FormProfileViewModel::class.java)

        viewModel.setSharedPreference(sharedpref)

        if (service != null) {
            viewModel.setProfileService(service)
        }

        if (serviceRoute != null) {
            viewModel.setRouteService(serviceRoute)
        }

        binding.btnSave.setOnClickListener {
            startActivity(Intent(this, ForgotPassActivity::class.java))

            val idUser = sharedpref.getString(Constants.KEY_ID)
            viewModel.callUpdateProfile(
                createPartFromString("6"),
                createPartFromString("5"),
                createPartFromString("dinanadi"),
                createPartFromString("0888888"),
                createPartFromString("purwodadi"),
                createPartFromString("1000"),
                img
            )
        }

            viewModel.initSpinnerLoc()
            setUpView()
            setUpListener()
            subcribeLiveData()

    }


    private fun setUpView() {
        val register = sharedpref.getBoolean(Constants.PREF_REGISTER)
        val submit = sharedpref.getBoolean(Constants.PREF_SUBMIT)

        when (register) {
            true -> {
//                if (submit == false) {
                    binding.btnSave.visibility = View.GONE
                    binding.btnSubmit.visibility = View.VISIBLE
//                }
            }
            else -> {
//                if (submit == true) {
                    binding.btnSave.visibility = View.VISIBLE
                    binding.btnSubmit.visibility = View.GONE
//                }
//                else {
//                    binding.btnSave.visibility = View.VISIBLE
//                    binding.btnSubmit.visibility = View.GONE
//                }

            }
        }
    }

    private fun setUpListener(){
        binding.btnSubmit.setOnClickListener {
            val idUser = sharedpref.getString(Constants.KEY_ID)
            viewModel.callPostProfile(
                createPartFromString("$idUser"),
                createPartFromString(selectedLoc),
                createPartFromString(binding.etProfileUsername.text.toString()),
                createPartFromString(binding.etProfilePhone.text.toString()),
                createPartFromString(binding.etProfileAdress.text.toString()),
                createPartFromString(binding.etProfilePostCode.text.toString()),
                img
            )
        }
        binding.tvChangeProfilePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImgGallery()
                }
            } else {
                pickImgGallery()
            }
        }

    }



    private fun subcribeLiveData(){
        viewModel.isFormProfile.observe(this, Observer {
            if (it) {
                sharedpref.putBoolean(Constants.PREF_SUBMIT, true)
                Toast.makeText(this, "Profile Data Sent!", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isResponseSpinner.observe(this, Observer {
            val list = it.data?.map {
                RouteModel(it.idRoutes.orEmpty(), it.city.orEmpty())
            } ?: listOf()
            val spinner = binding.etProfileCity
            spinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list.map {
                it.city
            })
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedLoc = list[position].id_route.toString()
                }

            }
        })

        viewModel.isUpdateProfile.observe(this, Observer {
            if(it) {
                Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed Updated!", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun pickImgGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
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
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
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