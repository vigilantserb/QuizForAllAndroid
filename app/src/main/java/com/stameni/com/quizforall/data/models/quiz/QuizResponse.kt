package com.stameni.com.quizforall.data.models.quiz


import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("data")
    val `data`: List<SingleQuiz>,
    @SerializedName("page")
    val page: String
)