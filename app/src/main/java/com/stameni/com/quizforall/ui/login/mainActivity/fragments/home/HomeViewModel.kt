package com.stameni.com.quizforall.ui.login.mainActivity.fragments.home

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PageKeyedDataSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz


class HomeViewModel(
    quizApi: QuizApi
) : ViewModel() {

    var itemPagedList: LiveData<PagedList<SingleQuiz>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, SingleQuiz>>

    init {

        val itemDataSourceFactory =
            QuizDataSourceFactory(quizApi)
        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(QuizDataSource.PAGE_SIZE)
            .build()
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}