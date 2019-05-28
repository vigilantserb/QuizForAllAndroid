package com.stameni.com.quizforall.ui.login

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.Tools
import com.stameni.com.quizforall.common.base.BaseActivity
import com.stameni.com.quizforall.data.models.LoginResponseModel
import com.stameni.com.quizforall.ui.forgotPassword.ForgotPasswordActivity
import com.stameni.com.quizforall.ui.login.mainActivity.MainScreenActivity
import com.stameni.com.quizforall.ui.signUp.SignupActivity
import retrofit2.Response
import javax.inject.Inject


class LoginActivity : BaseActivity() {

    lateinit var viewModel: LoginActivityViewModel

    @Inject
    lateinit var viewModelFactory: LoginActivityViewModelFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getPresentationComponent().inject(this)
        var loadingDialog = Tools.showLoadingDialog(this, R.layout.dialog_loading)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginActivityViewModel::class.java)

        sign_up.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }

        forgot_password.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }

        btn_login.setOnClickListener {
            onLoginClick(loadingDialog)
        }

        viewModel.loginResponse().observe(this, Observer {
            loginResponse(loadingDialog, it)
        })
    }

    private fun onLoginClick(loadingDialog: Dialog) {
        val username = username_field.text.toString()
        val password = password_field.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            loadingDialog.show()
            val loginInfo = HashMap<String, Any>()
            loginInfo["username"] = username
            loginInfo["password"] = password

            viewModel.loginPlayer(loginInfo)

        } else {
            val emptyFieldsDialog = Tools.showCustomDialog(this, R.layout.dialog_empty_fields)
            emptyFieldsDialog.show()

            (emptyFieldsDialog.findViewById<View>(R.id.btn_close) as AppCompatButton).setOnClickListener { v ->
                emptyFieldsDialog.dismiss()
            }
        }
    }

    private fun loginResponse(loadingDialog: Dialog, it: Response<LoginResponseModel>?) {
        if (it != null) {
            loadingDialog.dismiss()
            when {
                it.code() == 200 -> {
                    with(sharedPreferences.edit()) {
                        if (it.body() != null)
                            putString("_id", it.body()!!.playerId)
                        apply()
                    }
                    Toast.makeText(this, "Log in successful " + it.code(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainScreenActivity::class.java))
                }
                it.code() == 422 -> {
                    Toast.makeText(this, "Missing required fields.", Toast.LENGTH_SHORT).show()
                }
                it.code() == 401 -> {
                    Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show()
                }
                it.code() == 403 -> {
                    Toast.makeText(this, "Player account not verified.", Toast.LENGTH_SHORT).show()
                }
                it.code() == 404 -> {
                    Toast.makeText(this, "Player not found.", Toast.LENGTH_SHORT).show()
                }
                it.code() == 418 -> {
                    Toast.makeText(this, "Your account has been banned.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
