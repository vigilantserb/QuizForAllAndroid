package com.stameni.com.quizforall.data.models


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("playerId")
    val playerId: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)