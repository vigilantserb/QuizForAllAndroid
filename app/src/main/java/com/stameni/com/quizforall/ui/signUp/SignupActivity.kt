package com.stameni.com.quizforall.ui.signUp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.Tools
import com.stameni.com.quizforall.common.base.BaseActivity
import com.stameni.com.quizforall.data.models.BasicResponseModel
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Response
import javax.inject.Inject

class SignupActivity : BaseActivity() {

    lateinit var viewModel: SignUpActivityViewModel

    @Inject
    lateinit var viewModelFactory: SignUpViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        getPresentationComponent().inject(this)
        var loadingDialog = Tools.showLoadingDialog(this, R.layout.dialog_loading)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpActivityViewModel::class.java)

        btn_sign_up.setOnClickListener {
            loadingDialog.show()
            var username = username_field.text.toString()
            var email = email_field.text.toString()
            var password = password_field.text.toString()
            var password2 = password2_field.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Please fill in the needed fields.", Toast.LENGTH_SHORT).show()
                loadingDialog.dismiss()
            } else {
                val signUpInfo = HashMap<String, Any>()
                signUpInfo["username"] = username
                signUpInfo["email"] = email
                signUpInfo["password"] = password
                signUpInfo["password2"] = password2

                viewModel.signUp(signUpInfo)
            }
        }

        viewModel.signUpResponse().observe(this, Observer {
            if (it != null) {
                signUpResponse(loadingDialog, it)
            }
        })
    }

    private fun signUpResponse(loadingDialog: Dialog, it: Response<BasicResponseModel>) {
        loadingDialog.dismiss()
        when {
            it.code() == 401 -> Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            it.code() == 409 -> Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            it.code() == 400 -> Toast.makeText(this, "Email or username taken.", Toast.LENGTH_SHORT).show()
            it.code() == 201 -> {
                val dialog = Tools.showCustomDialog(
                    this,
                    R.layout.dialog_account_verification
                )
                dialog.show()

                (dialog.findViewById<View>(R.id.btn_close) as AppCompatButton).setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }
}
