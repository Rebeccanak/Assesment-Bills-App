package com.example.assessment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
//import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.assessment.databinding.ActivityMainBinding
import com.example.assessment.model.RegisterRequest
import com.example.assessment.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.btnbutton.setOnClickListener {
            clearErrors()
            validateDetails()

        }

        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            binding.progressBar.visibility = View.GONE
        })
        userViewModel.regLiveData.observe(this,
            Observer { regResponse ->
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, regResponse.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        })
        binding.tvlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
//            val intent= Intent(this,LoginActivity::class.java)
//            startActivity(intent)
            finish()

        }
    }

    fun validateDetails() {
        val firstname = binding.etusername.text.toString()
        val phoneNumber = binding.etphonenumber.text.toString()
        val email = binding.etemail.text.toString()
        val password = binding.etpassword.text.toString()
        val confirmpassword = binding.etConfirmPassword.text.toString()
        val lastname = binding.etLastName.text.toString()
        var error = false

        if (firstname.isBlank()) {
            binding.tilusername.error = "First Name is required"
            error = true
        }
        if (lastname.isBlank()) {
            binding.tilLastName.error = "Last Name is required"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilphonenumber.error = "phone number is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tilemail.error = "Email is required"
            error = true
        }

        if (password.isBlank()) {
            binding.tilpassword.error = "password is required"
            error = true
        }
        if (confirmpassword.isBlank()) {
            binding.tilConfirmPassword.error = "password is required"
            error = true
        }
        if (confirmpassword != password) {
            binding.tilConfirmPassword.error = "passwords must be equal"
            error = true
        }

        if (!error) {
//        val registerRequest=RegisterRequest(firstname,lastname,phoneNumber,email,password,confirmpassword)
            val registerRequest = RegisterRequest(
                firstname = firstname,
                lastname = lastname,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            )
            binding.progressBar.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)

        }
    }

    fun clearErrors() {
        binding.tilusername.error = null
        binding.tilLastName.error = null
        binding.tilpassword.error = null
        binding.tilemail.error = null
        binding.tilphonenumber.error = null
        binding.tilConfirmPassword.error = null
    }
}


