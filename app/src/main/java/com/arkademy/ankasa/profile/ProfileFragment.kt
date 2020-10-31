package com.arkademy.ankasa.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.FragmentProfileBinding
import com.arkademy.ankasa.onboard.OnBoardActivity
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var sharePreferencesHelper: PreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container,false)
        sharePreferencesHelper = PreferenceHelper(requireContext())
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val service = ApiClient.getApiClientToken(requireContext())?.create(ProfileAPIService::class.java)
        viewModel.setSharedPreference(sharePreferencesHelper)

        if (service != null) {
            viewModel.setProfileService(service)
        }

        binding.textView12.setOnClickListener {
            val intent = Intent(activity, FormProfileActivity::class.java)
            startActivity(intent)
        }
        binding.Logout.setOnClickListener {
            dialog()
        }
        viewModel.callProfileApi()
        subscribelivedata()
        return binding.root
    }

    private fun subscribelivedata(){
        viewModel.isResponseProfile.observe(this, Observer {
            binding.tvProfileName.text = it.data?.fullname.toString()
            binding.tvKota.text = it.data?.city.toString()
            Picasso.get().load("http://34.227.91.246:8080/uploads/" + it.data?.image).placeholder(R.drawable.ic_baseline_person_24).into(binding.ivProfile)
        })
    }

    private fun dialog(){
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Are You Sure?")
            .setPositiveButton("Log Out") { dialog: DialogInterface?, which: Int ->
                sharePreferencesHelper.clear()
                val intent=Intent(requireActivity(), OnBoardActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Cancel") {dialogInterface, i -> dialogInterface.dismiss()
            }
        dialog.show()
    }
}