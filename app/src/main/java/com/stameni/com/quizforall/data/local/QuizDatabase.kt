package com.stameni.com.quizforall.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz

@Database(entities = [SingleQuiz::class, Page::class], version = 1)
@TypeConverters(Converters::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        private var instance: RoomDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): RoomDatabase {
            return Room.databaseBuilder(context.applicationContext, QuizDatabase::class.java, "quiz.db").build()
        }
    }
}