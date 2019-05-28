package com.stameni.com.quizforall.ui.forgotPassword.passwordReset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stameni.com.quizforall.data.repository.QuizRepository

class PasswordResetActivityViewModelFactory(
    private val quizRepository: QuizRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PasswordResetActivityViewModel(quizRepository) as (T)
    }
}