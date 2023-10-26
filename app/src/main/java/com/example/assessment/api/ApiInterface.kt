package com.example.assessment.api

import com.example.assessment.model.Bill
import com.example.assessment.model.LoginRequest
import com.example.assessment.model.LoginResponse
import com.example.assessment.model.RegisterRequest
import com.example.assessment.model.RegisterResponse
import com.example.assessment.model.UpcomingBill
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
   suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

   @POST("/users/login")
   suspend fun loginUser(@Body loginRequest: LoginRequest):Response<LoginResponse>

   @POST("/bills")
   suspend fun postBill(@Header("Authorization") token: String,@Body bill: Bill):Response<Bill>

   @POST("/upcoming_bills")
   suspend fun postUpcomingBill(@Header("Authorization") token: String, @Body upcomingBill: UpcomingBill):Response<UpcomingBill>


   @GET("/bills")
   suspend fun fetchRemoteBills(@Header("Authorization") token: String): Response<List<Bill>>

   @GET("/upcoming-bills")
   suspend fun fetchRemoteUpcomingBills(@Header("Authorization") token: String): Response<List<UpcomingBill>>
}