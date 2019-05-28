package com.stameni.com.quizforall.ui.login.mainActivity.fragments.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stameni.com.quizforall.data.QuizApi

class ExploreViewModelFactory(
    private val quizApi: QuizApi
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExploreViewModel(quizApi) as (T)
    }
}