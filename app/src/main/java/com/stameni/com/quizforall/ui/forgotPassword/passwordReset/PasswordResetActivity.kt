package com.stameni.com.quizforall.ui.forgotPassword.passwordReset

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
import com.stameni.com.quizforall.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_password_reset.*
import retrofit2.Response
import javax.inject.Inject

class PasswordResetActivity : BaseActivity() {

    lateinit var viewModel: PasswordResetActivityViewModel

    @Inject
    lateinit var viewModelFactory: PasswordResetActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)
        getPresentationComponent().inject(this)
        val loadingDialog = Tools.showLoadingDialog(this, R.layout.dialog_loading)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PasswordResetActivityViewModel::class.java)
        btn_update_password.setOnClickListener {
            onUpdatePasswordClick(loadingDialog)
        }

        viewModel.updatePasswordResponse().observe(this, Observer {
            if (it != null) {
                updatePasswordResponse(loadingDialog, it)
            }
        })
    }

    private fun onUpdatePasswordClick(loadingDialog: Dialog) {
        loadingDialog.show()
        val email = intent.getStringExtra("email")
        val token = token_field.text.toString()
        val password = password_field.text.toString()
        val password2 = password2_field.text.toString()

        if (email.isEmpty() || token.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            loadingDialog.dismiss()
            Toast.makeText(this, "Missing required fields.", Toast.LENGTH_SHORT).show()
        } else {
            if (password == password2) {
                val updatePasswordInfo = HashMap<String, Any>()
                updatePasswordInfo["token"] = token
                updatePasswordInfo["password"] = password
                updatePasswordInfo["password2"] = password2
                updatePasswordInfo["email"] = email

                viewModel.updatePassword(updatePasswordInfo)
            } else {
                loadingDialog.dismiss()
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun updatePasswordResponse(loadingDialog: Dialog, it: Response<BasicResponseModel>) {
        loadingDialog.dismiss()
        when {
            it.code() == 200 -> {
                Toast.makeText(this, "Password update successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@PasswordResetActivity, LoginActivity::class.java))
            }
            it.code() == 401 ->
                Toast.makeText(this, "Token expired.", Toast.LENGTH_SHORT).show()
            it.code() == 404 ->
                Toast.makeText(this, "Token not acquired.", Toast.LENGTH_SHORT).show()
            it.code() == 420 ->
                Toast.makeText(this, "Update token not acquired.", Toast.LENGTH_SHORT).show()
            it.code() == 421 ->
                Toast.makeText(this, "Tokens do not match.", Toast.LENGTH_SHORT).show()
            it.code() == 422 ->
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            it.code() == 423 ->
                Toast.makeText(this, "Email not found.", Toast.LENGTH_SHORT).show()
        }
    }
}
