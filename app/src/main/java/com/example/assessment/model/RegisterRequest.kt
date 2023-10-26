package com.example.assessment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
   @Expose @SerializedName("first_name") var firstname:String,
   @Expose @SerializedName("last_name") var lastname:String,
   @Expose var email:String,
   @Expose @SerializedName("phone_number") var phoneNumber:String,
   @Expose var password:String,
)
