package com.stameni.com.quizforall.data.models

import com.google.gson.annotations.SerializedName

data class BasicResponseModel (
    @SerializedName("message")
    val message: String
)