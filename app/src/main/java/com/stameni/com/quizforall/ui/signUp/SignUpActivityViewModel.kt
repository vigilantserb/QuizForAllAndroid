package com.stameni.com.quizforall.ui.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.repository.QuizRepository
import retrofit2.Response

class SignUpActivityViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun signUp(signUpInfo: HashMap<String, Any>){
        return quizRepository.signUp(signUpInfo)
    }

    fun signUpResponse(): LiveData<Response<BasicResponseModel>> {
        return quizRepository.signUpResponse
    }
}