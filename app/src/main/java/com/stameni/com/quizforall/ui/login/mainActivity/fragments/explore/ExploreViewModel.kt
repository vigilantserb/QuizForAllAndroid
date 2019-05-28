package com.stameni.com.quizforall.ui.login.mainActivity.fragments.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz

class ExploreViewModel(
    quizApi: QuizApi
) : ViewModel() {

    var itemPagedList: LiveData<PagedList<SingleQuiz>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, SingleQuiz>>

    init {

        val itemDataSourceFactory =
            ExploreQuizzesDataSourceFactory(quizApi)
        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ExploreQuizzesDataSource.PAGE_SIZE)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}