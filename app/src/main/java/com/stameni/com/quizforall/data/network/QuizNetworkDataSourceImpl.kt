package com.stameni.com.quizforall.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stameni.com.quizforall.data.QuizApi
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.data.models.LoginResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class QuizNetworkDataSourceImpl(
    private val quizApi: QuizApi
) : QuizNetworkDataSource {

    private val _signUpResponse: MutableLiveData<Response<BasicResponseModel>> = MutableLiveData()

    override val signUpResponse: LiveData<Response<BasicResponseModel>>
        get() = _signUpResponse

    override fun signUp(signUpInfo: HashMap<String, Any>) {
        subscription = quizApi.signUp(signUpInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSignUpStart() }
            .doOnTerminate { onSignUpFinish() }
            .doOnError { err -> onSignUpError(err as Exception) }
            .subscribe(
                { result -> onSignUpSuccess(result) },
                { err ->
                    onSignUpError(err as Exception)
                })
    }

    private fun onSignUpSuccess(result: Response<BasicResponseModel>) {
        Log.e("SIGN UP", "SUCCESS")
        _signUpResponse.value = result
    }

    private fun onSignUpError(exception: Exception) {
        Log.e("SIGN UP", "ERROR", exception)
    }

    private fun onSignUpFinish() {
        Log.e("SIGN UP", "FINISH")
    }

    private fun onSignUpStart() {
        Log.e("SIGN UP", "START")
    }

    private val _updatePasswordResponse: MutableLiveData<Response<BasicResponseModel>> = MutableLiveData()

    override val updatePasswordResponse: LiveData<Response<BasicResponseModel>>
        get() = _updatePasswordResponse

    override fun updatePassword(updatePasswordInfo: HashMap<String, Any>) {
        subscription = quizApi.updatePassword(updatePasswordInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onUpdatePasswordStart() }
            .doOnTerminate { onUpdatePasswordFinish() }
            .doOnError { err -> onUpdatePasswordError(err as Exception) }
            .subscribe(
                { result -> onUpdatePasswordSuccess(result) },
                { err ->
                    onUpdatePasswordError(err as Exception)
                })
    }

    private fun onUpdatePasswordSuccess(result: Response<BasicResponseModel>) {
        Log.e("UPDATE PASSWORD", "SUCCESS")
        _updatePasswordResponse.value = result
    }

    private fun onUpdatePasswordError(exception: Exception) {
        Log.e("UPDATE PASSWORD", "ERROR", exception)
    }

    private fun onUpdatePasswordFinish() {
        Log.e("UPDATE PASSWORD", "FINISHED")
        _updatePasswordResponse.value = null
    }

    private fun onUpdatePasswordStart() {
        Log.e("UPDATE PASSWORD", "STARTED")
    }

    private val _sendRecoveryEmailResponse: MutableLiveData<Response<BasicResponseModel>> = MutableLiveData()

    override val sendRecoveryEmailResponse: LiveData<Response<BasicResponseModel>>
        get() = _sendRecoveryEmailResponse

    override fun sendRecoveryEmail(emailInfo: HashMap<String, Any>) {
        subscription = quizApi.recoveryEmail(emailInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRecoveryEmailSendStart() }
            .doOnTerminate { onRecoveryEmailSendFinish() }
            .doOnError { err -> onRecoveryEmailSendError(err as Exception) }
            .subscribe(
                { result -> onRecoveryEmailSendSuccess(result) },
                { err ->
                    onRecoveryEmailSendError(err as Exception)
                })
    }

    private fun onRecoveryEmailSendSuccess(result: Response<BasicResponseModel>) {
        Log.e("SEND RECOVERY EMAIL", "SUCCESS")
        _sendRecoveryEmailResponse.value = result
    }

    private fun onRecoveryEmailSendError(exception: Exception) {
        Log.e("SEND RECOVERY EMAIL", "ERROR", exception)
    }

    private fun onRecoveryEmailSendFinish() {
        Log.e("SEND RECOVERY EMAIL", "FINISHED")
        _sendRecoveryEmailResponse.value = null
    }

    private fun onRecoveryEmailSendStart() {
        Log.e("SEND RECOVERY EMAIL", "STARTED")
    }

    private val _loginResponse: MutableLiveData<Response<LoginResponseModel>> = MutableLiveData()

    override val loginResponse: LiveData<Response<LoginResponseModel>>
        get() = _loginResponse

    override lateinit var subscription: Disposable


    override fun loginPlayer(loginInfo: HashMap<String, Any>) {
        subscription = quizApi.loginPlayer(loginInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onLoginPlayerStart() }
            .doOnTerminate { onLoginPlayerFinish() }
            .doOnError { err -> onLoginPlayerError(err as Exception) }
            .subscribe(
                { result -> onLoginPlayerSuccess(result) },
                { err ->
                    onLoginPlayerError(err as Exception)
                })
    }

    private fun onLoginPlayerSuccess(result: Response<LoginResponseModel>) {
        Log.e("LOG IN", "SUCCESS")
        _loginResponse.value = result
    }

    private fun onLoginPlayerError(exception: Exception) {
        Log.e("LOG IN", "ERROR", exception)
    }

    private fun onLoginPlayerFinish() {
        Log.e("LOG IN", "FINISHED")
    }

    private fun onLoginPlayerStart() {
        Log.e("LOG IN", "STARTED")
    }
}