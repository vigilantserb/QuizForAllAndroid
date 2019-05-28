package com.stameni.com.quizforall.data.models.quiz


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("playerId")
    val playerId: String,
    @SerializedName("rating")
    val rating: String
)