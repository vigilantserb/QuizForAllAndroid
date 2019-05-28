package com.stameni.com.quizforall.ui.login.mainActivity.fragments.favorites

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stameni.com.quizforall.data.QuizApi

class FavoritesViewModelFactory (
    private val quizApi: QuizApi,
    private val sharedPreferences: SharedPreferences
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(quizApi, sharedPreferences) as (T)
    }
}