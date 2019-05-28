package com.stameni.com.quizforall.ui.login.mainActivity.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz


class QuizDataSourceFactory(
    private val quizApi: QuizApi
) : DataSource.Factory<Int, SingleQuiz>() {

    val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, SingleQuiz>>()

    override fun create(): DataSource<Int, SingleQuiz> {
        val itemDataSource = QuizDataSource(quizApi)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }
}