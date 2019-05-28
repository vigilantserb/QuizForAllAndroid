package com.stameni.com.quizforall.ui.forgotPassword.passwordReset

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.repository.QuizRepository
import retrofit2.Response

class PasswordResetActivityViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun updatePassword(emailInfo: HashMap<String, Any>){
        return quizRepository.updatePassword(emailInfo)
    }

    fun updatePasswordResponse(): LiveData<Response<BasicResponseModel>> {
        return quizRepository.updatePasswordResponse
    }
}