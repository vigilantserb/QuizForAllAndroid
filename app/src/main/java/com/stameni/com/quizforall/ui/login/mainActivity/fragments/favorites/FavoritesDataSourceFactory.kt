package com.stameni.com.quizforall.ui.login.mainActivity.fragments.favorites

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz

class FavoritesDataSourceFactory(
    private val quizApi: QuizApi,
    private val playerId: String
) : DataSource.Factory<Int, SingleQuiz>() {

    val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, SingleQuiz>>()

    override fun create(): DataSource<Int, SingleQuiz> {
        val itemDataSource = FavoritesDataSource(quizApi, playerId)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }
}