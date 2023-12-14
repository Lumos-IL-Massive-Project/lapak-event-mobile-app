package com.example.connectus.activities.signin

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.connectus.BuildConfig
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.otp.OTPActivity
import com.example.connectus.activities.signin.viewmodels.SignInViewModel
import com.example.connectus.activities.signup.SignUpActivity
import com.example.connectus.databinding.ActivitySignInBinding
import com.example.connectus.network.ApiResult
import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.bodyrequest.LoginBody
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.Constants.EMAIL_TO_REGISTER
import com.example.connectus.utils.Constants.OTP_CODE
import com.example.connectus.utils.GlobalPopup.dismissLoadingPopup
import com.example.connectus.utils.GlobalPopup.showLoadingPopup
import com.example.connectus.utils.GlobalPopup.showWarningPopup
import com.example.connectus.utils.startDynamicActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel by viewModels<SignInViewModel>()
    private var emailToCheck: String? = null

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCheckEmailResultObserver()
        initLoginResultObserver()
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
                    emailToCheck = account.email
                    if (!emailToCheck.isNullOrBlank()) {
                        viewModel.checkEmail(EmailCheckBody(emailToCheck!!, "mobile"))
                    } else {
                        Toast.makeText(this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Login google gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initCheckEmailResultObserver() {
        viewModel.emailCheckResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    dismissLoadingPopup()
                    showWarningPopup(
                        this,
                        layoutInflater,
                        false,
                        result.message.toString(),
                    ) {
                        if (result.message == "Email tidak terdaftar") {
                            startDynamicActivity(
                                this,
                                SignUpActivity::class.java,
                                data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, emailToCheck)
                                )
                            )
                        }
                    }
                }

                is ApiResult.Loading -> {
                    showLoadingPopup(this, layoutInflater, true)
                }

                is ApiResult.Success -> {
                    dismissLoadingPopup()
                    if (result.data.message == "Berhasil mengirim kode OTP, silahkan cek email anda") {
                        if (BuildConfig.DEBUG) {
                            startDynamicActivity(
                                this, OTPActivity::class.java, data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, emailToCheck),
                                    Pair(OTP_CODE, result.data.data?.otp)
                                )
                            )
                        } else {
                            startDynamicActivity(
                                this, OTPActivity::class.java, data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, emailToCheck)
                                )
                            )
                        }
                    } else {
                        val gson = Gson()
                        val userDataJson = gson.toJson(result.data.data)
                        appPreferenceManager.saveAuthCredentials(userDataJson)

                        startDynamicActivity(this, MainActivity::class.java)
                        finish()
                    }
                }
            }
        })
    }

    private fun initLoginResultObserver() {
        viewModel.loginResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    dismissLoadingPopup()
                    showWarningPopup(this, layoutInflater, false, result.message.toString(), null)
                }

                is ApiResult.Loading -> {
                    showLoadingPopup(this, layoutInflater, true)
                }

                is ApiResult.Success -> {
                    dismissLoadingPopup()
                    if (result.data.message == "Berhasil mengirim kode OTP, silahkan cek email anda") {
                        val email = binding.etEmail.text.toString()

                        if (BuildConfig.DEBUG) {
                            startDynamicActivity(
                                this, OTPActivity::class.java, data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, email),
                                    Pair(OTP_CODE, result.data.data?.otp)
                                )
                            )
                        } else {
                            startDynamicActivity(
                                this, OTPActivity::class.java, data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, email)
                                )
                            )
                        }
                    } else {
                        val gson = Gson()
                        val userDataJson = gson.toJson(result.data.data)
                        appPreferenceManager.saveAuthCredentials(userDataJson)

                        startDynamicActivity(this, MainActivity::class.java)
                        finish()
                    }
                }
            }
        })
    }

    private fun initForm() {
        val email = intent.getStringExtra(EMAIL_TO_REGISTER)
        binding.etEmail.setText(email)

        binding.togglePassword.setOnClickListener {
            if (binding.etPassword.transformationMethod === PasswordTransformationMethod.getInstance()) {
                binding.etPassword.transformationMethod = null
                binding.togglePassword.setImageResource(R.drawable.ic_eye_24)
            } else {
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.togglePassword.setImageResource(R.drawable.ic_eye_off_24)
            }

            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
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
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.error = "Email tidak boleh kosong"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            viewModel.login(LoginBody(email, password, "mobile"))
        }
    }

    private fun initSignUpButton() {
        binding.tvSignUpBtn.setOnClickListener {
            startDynamicActivity(this, SignUpActivity::class.java)
        }
    }

    companion object {
        private const val RC_SIGN_IN = 354
    }
}