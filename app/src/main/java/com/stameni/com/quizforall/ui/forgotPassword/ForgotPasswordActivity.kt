package com.stameni.com.quizforall.ui.forgotPassword

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.Tools
import com.stameni.com.quizforall.common.base.BaseActivity
import com.stameni.com.quizforall.data.models.BasicResponseModel
import com.stameni.com.quizforall.ui.forgotPassword.passwordReset.PasswordResetActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*
import retrofit2.Response
import javax.inject.Inject

class ForgotPasswordActivity : BaseActivity() {

    lateinit var viewModel: ForgotPasswordViewModel

    @Inject
    lateinit var viewModelFactory: ForgotPasswordViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        getPresentationComponent().inject(this)
        var email = ""
        val loadingDialog = Tools.showLoadingDialog(this, R.layout.dialog_loading)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForgotPasswordViewModel::class.java)

        btn_forgot_password.setOnClickListener {
            loadingDialog.show()
            email = email_field.text.toString()
            val emailInfo = HashMap<String, Any>()
            emailInfo["email"] = email

            if (email.isNotEmpty()) {
                viewModel.sendRecoveryEmail(emailInfo)
            } else {
                Toast.makeText(this, "Please fill in your email address", Toast.LENGTH_SHORT).show()
            }
        }


        viewModel.sendRecoveryEmailResponse().observe(this, Observer {
            if (it != null)
                sendRecoveryEmailResponse(loadingDialog, email, it)
        })
    }

    private fun sendRecoveryEmailResponse(loadingDialog: Dialog, email: String, it: Response<BasicResponseModel>) {
        loadingDialog.dismiss()
        when {
            it.code() == 200 -> {
                Toast.makeText(this, "Email sent successfully.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ForgotPasswordActivity, PasswordResetActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            }
            it.code() == 404 -> Toast.makeText(this, "Player with that email not found.", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Internal server error.", Toast.LENGTH_SHORT).show()
        }
    }
}
