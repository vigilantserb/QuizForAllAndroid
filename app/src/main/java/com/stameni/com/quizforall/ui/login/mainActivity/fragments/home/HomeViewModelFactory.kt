package com.stameni.com.quizforall.ui.login.mainActivity.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stameni.com.quizforall.data.QuizApi

class HomeViewModelFactory(
    private val quizApi: QuizApi
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(quizApi) as (T)
    }
}