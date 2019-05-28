package com.stameni.com.quizforall.data.models.quiz


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SingleQuiz(
    @SerializedName("_id")
    @PrimaryKey
    val id: String,
    @SerializedName("numberOfPlays")
    val numberOfPlays: String,
    @SerializedName("quizName")
    val quizName: String,
    @SerializedName("quizType")
    val quizType: String,
    @SerializedName("lastEdited")
    val lastEdited: String,
    @SerializedName("ratings")
    val ratings: List<Rating>
)