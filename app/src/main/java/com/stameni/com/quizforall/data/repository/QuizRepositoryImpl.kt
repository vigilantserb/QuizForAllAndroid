package com.stameni.com.quizforall.data.repository

import androidx.lifecycle.LiveData
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.models.LoginResponseModel
import com.stameni.com.quizforall.data.network.QuizNetworkDataSource
import retrofit2.Response

class QuizRepositoryImpl(
    private val quizNetworkDataSource: QuizNetworkDataSource
) : QuizRepository {
    override fun signUp(signUpInfo: HashMap<String, Any>) {
        quizNetworkDataSource.signUp(signUpInfo)
    }

    override val signUpResponse: LiveData<Response<BasicResponseModel>>
        get() = quizNetworkDataSource.signUpResponse

    override fun updatePassword(updatePasswordInfo: HashMap<String, Any>) {
        quizNetworkDataSource.updatePassword(updatePasswordInfo)
    }

    override val updatePasswordResponse: LiveData<Response<BasicResponseModel>>
        get() = quizNetworkDataSource.updatePasswordResponse

    override val sendRecoveryEmailResponse: LiveData<Response<BasicResponseModel>>
        get() = quizNetworkDataSource.sendRecoveryEmailResponse

    override fun sendRecoveryEmail(emailInfo: HashMap<String, Any>) {
        quizNetworkDataSource.sendRecoveryEmail(emailInfo)
    }

    override val loginResponse: LiveData<Response<LoginResponseModel>>
        get() = quizNetworkDataSource.loginResponse

    override fun loginPlayer(loginInfo: HashMap<String, Any>) {
        quizNetworkDataSource.loginPlayer(loginInfo)
    }
}