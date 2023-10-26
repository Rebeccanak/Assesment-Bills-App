package com.example.assessment.model

import com.google.gson.annotations.Expose

data class UserLogin(
     @Expose var email:String,
     @Expose var password: String,
//    @SerializedName("user_id") var userId: String

)
