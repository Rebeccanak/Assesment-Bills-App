package com.example.assessment.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.room.util.EMPTY_STRING_ARRAY
import com.anychart.BuildConfig
import com.example.assessment.R
import com.example.assessment.databinding.ActivityAddBillBinding
import com.example.assessment.databinding.FragmentSettingsBinding
import com.example.assessment.model.LoginResponse
import com.example.assessment.utils.Constants
import com.example.assessment.utils.Constants.Companion.EMPTY_STRING
import com.example.assessment.viewmodel.BillsViewModel
import com.google.android.material.snackbar.Snackbar


class SettingsFragment : Fragment() {
    var binding: FragmentSettingsBinding? = null
    val billsViewModel : BillsViewModel by viewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        binding?.tvappversion?. text ="APP VERSION : ${com.example.assessment.BuildConfig.VERSION_NAME}"
        binding?.tvlogout?.setOnClickListener{logout()}

        binding?.Tvsync?.setOnClickListener{syncData()}
    }

    fun logout(){
        val sharedPrefs =requireContext(). getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(Constants.USER_ID, EMPTY_STRING)
        editor.putString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        editor.apply()
        requireContext().startActivity(Intent(requireContext() ,LoginActivity::class.java))
    }
    fun syncData(){
        binding?.pbsync?.visibility =View.VISIBLE
        billsViewModel.fetchRemoteBills()
        val timer =object :CountDownTimer(10000,10000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {

                binding?.pbsync?.hide()
                Snackbar.make(binding!!.settingsRoot,"Sync completed" , Snackbar.LENGTH_SHORT).show()
            }
        }
        timer.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding =null
    }
}