package com.stameni.com.quizforall.di.module

import android.content.Context
import com.stameni.com.quizforall.data.local.QuizDao
import com.stameni.com.quizforall.data.local.QuizDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule{
    @Singleton
    @Provides
    fun getQuizDatabase(context: Context): QuizDatabase = QuizDatabase(context) as QuizDatabase

    @Singleton
    @Provides
    fun getQuizDao(quizDatabase: QuizDatabase): QuizDao = quizDatabase.quizDao()
}