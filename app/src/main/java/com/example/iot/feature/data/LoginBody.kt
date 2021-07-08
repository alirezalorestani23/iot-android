package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String
)
