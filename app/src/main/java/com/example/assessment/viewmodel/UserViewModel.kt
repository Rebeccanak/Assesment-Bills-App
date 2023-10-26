package com.example.assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.model.LoginRequest
import com.example.assessment.model.LoginResponse
import com.example.assessment.model.RegisterRequest
import com.example.assessment.model.RegisterResponse
import com.example.assessment.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepo=UserRepository()
    val regLiveData= MutableLiveData<RegisterResponse>()
    val errLiveData=MutableLiveData<String>()
    val loginLiveData=MutableLiveData<LoginResponse>()


    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response= userRepo.registerUser(registerRequest)

            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response= userRepo.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}