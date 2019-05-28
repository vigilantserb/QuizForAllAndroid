package com.stameni.com.quizforall.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.stameni.com.quizforall.data.models.quiz.Rating
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz


class Converters {

    @TypeConverter
    fun listToJson(value: List<Rating>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Rating>? {
        val objects = Gson().fromJson(value, Array<Rating>::class.java) as Array<Rating>
        return objects.toList()
    }
}