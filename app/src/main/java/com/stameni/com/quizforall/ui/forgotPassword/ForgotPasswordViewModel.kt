package com.stameni.com.quizforall.ui.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.repository.QuizRepository
import retrofit2.Response

class ForgotPasswordViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun sendRecoveryEmail(emailInfo: HashMap<String, Any>){
        return quizRepository.sendRecoveryEmail(emailInfo)
    }

    fun sendRecoveryEmailResponse(): LiveData<Response<BasicResponseModel>> {
        return quizRepository.sendRecoveryEmailResponse
    }
}