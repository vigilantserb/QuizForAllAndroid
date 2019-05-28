package com.stameni.com.quizforall.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stameni.com.quizforall.data.models.LoginResponseModel
import com.stameni.com.quizforall.data.repository.QuizRepository
import retrofit2.Response

class LoginActivityViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun loginPlayer(loginInfo: HashMap<String, Any>){
        return quizRepository.loginPlayer(loginInfo)
    }

    fun loginResponse(): LiveData<Response<LoginResponseModel>>{
        return quizRepository.loginResponse
    }
}