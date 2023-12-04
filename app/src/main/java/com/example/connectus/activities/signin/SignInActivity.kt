package com.example.connectus.activities.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.signin.viewmodels.SignInViewModel
import com.example.connectus.activities.signup.SignUpActivity
import com.example.connectus.databinding.ActivitySignInBinding
import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.utils.ApiResponseHandler
import com.example.connectus.utils.startDynamicActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initForm()
        initSignInButton()
        initSignInButtonWithGoogle()
        initSignUpButton()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val email = account.email
                    if (!email.isNullOrBlank()) {
                        checkEmailAndSignIn(email)
                    } else {
                        Toast.makeText(this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Login google gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initForm() {
        val email = intent.getStringExtra(EMAIL_TO_REGISTER)
        binding.etEmail.setText(email)
    }

    private fun initSignInButtonWithGoogle() {
        binding.signInButtonWithGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(this, gso)

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun initSignInButton() {
        binding.signInButton.setOnClickListener {
            startDynamicActivity(this, MainActivity::class.java)
        }
    }

    private fun initSignUpButton() {
        binding.tvSignUpBtn.setOnClickListener {
            startDynamicActivity(this, SignUpActivity::class.java)
        }
    }

    private fun checkEmailAndSignIn(email: String) {
        viewModel.viewModelScope.launch {
            try {
                val emailRequestBody = EmailCheckBody(email)
                val emailCheckResponse = viewModel.checkEmail(emailRequestBody)

                if (emailCheckResponse.success == true) {
                    startDynamicActivity(this@SignInActivity, MainActivity::class.java)
                } else {
                    Toast.makeText(applicationContext, "Email belum terdaftar", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: retrofit2.HttpException) {
                ApiResponseHandler.handleApiError(
                    applicationContext,
                    e,
                    object : ApiResponseHandler.ErrorCallback {
                        override fun onFailure(message: String) {
                            if (message == "Email tidak terdaftar") {
                                startDynamicActivity(
                                    this@SignInActivity,
                                    SignUpActivity::class.java,
                                    data = arrayOf(
                                        Pair(EMAIL_TO_REGISTER, email)
                                    )
                                )
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Error: $message",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
            } catch (e: Exception) {
                Log.e("SignInActivity", "Error: ${e.message}")
                Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 354
        private const val EMAIL_TO_REGISTER = "EMAIL_TO_REGISTER"
    }
}