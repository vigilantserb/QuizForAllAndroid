package com.stameni.com.quizforall.data.repository

import androidx.lifecycle.LiveData
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.models.LoginResponseModel
import retrofit2.Response

interface QuizRepository {
    fun loginPlayer(loginInfo: HashMap<String, Any>)
    fun sendRecoveryEmail(emailInfo: HashMap<String, Any>)
    fun updatePassword(updatePasswordInfo: HashMap<String, Any>)
    fun signUp(signUpInfo: HashMap<String, Any>)

    val loginResponse: LiveData<Response<LoginResponseModel>>
    val sendRecoveryEmailResponse: LiveData<Response<BasicResponseModel>>
    val updatePasswordResponse: LiveData<Response<BasicResponseModel>>
    val signUpResponse: LiveData<Response<BasicResponseModel>>
}