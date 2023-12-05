package com.example.connectus.activities.signup

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.connectus.BuildConfig
import com.example.connectus.R
import com.example.connectus.activities.otp.OTPActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.activities.signup.viewmodels.SignUpViewModel
import com.example.connectus.databinding.ActivitySignUpBinding
import com.example.connectus.network.ApiResult
import com.example.connectus.network.bodyrequest.RegisterBody
import com.example.connectus.utils.GlobalPopup.dismissLoadingPopup
import com.example.connectus.utils.GlobalPopup.showLoadingPopup
import com.example.connectus.utils.GlobalPopup.showWarningPopup
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initForm()
        initSignUpButton()
        initSignInButton()
    }

    private fun initViewModel() {
        viewModel.registrationResult.observe(this, Observer { result ->
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
                    showWarningPopup(this, layoutInflater, true, result.data.message.toString()) {
                        val emailToRegister = intent.getStringExtra(EMAIL_TO_REGISTER)

                        if (BuildConfig.DEBUG) {
                            startDynamicActivity(
                                this, OTPActivity::class.java, data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, emailToRegister),
                                    Pair(OTP_CODE, result.data.data?.otp)
                                )
                            )
                        } else {
                            startDynamicActivity(
                                this, OTPActivity::class.java, data = arrayOf(
                                    Pair(EMAIL_TO_REGISTER, emailToRegister)
                                )
                            )
                        }
                    }
                }
            }
        })
    }

    private fun initForm() {
        val emailToRegister = intent.getStringExtra(EMAIL_TO_REGISTER)
        binding.etEmail.setText(emailToRegister)

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

        binding.toggleConfirmationPassword.setOnClickListener {
            if (binding.etConfirmationPassword.transformationMethod === PasswordTransformationMethod.getInstance()) {
                binding.etConfirmationPassword.transformationMethod = null
                binding.toggleConfirmationPassword.setImageResource(R.drawable.ic_eye_24)
            } else {
                binding.etConfirmationPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.toggleConfirmationPassword.setImageResource(R.drawable.ic_eye_off_24)
            }

            binding.etConfirmationPassword.setSelection(binding.etConfirmationPassword.text.length)
        }
    }

    private fun initSignUpButton() {
        binding.signUpButton.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmationPassword = binding.etConfirmationPassword.text.toString()

            if (username.isEmpty()) {
                binding.etUsername.error = "Username tidak boleh kosong"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.etEmail.error = "Email tidak boleh kosong"
                return@setOnClickListener
            }

            if (phoneNumber.isEmpty()) {
                binding.etPhoneNumber.error = "Nomor telepon tidak boleh kosong"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            if (confirmationPassword.isEmpty()) {
                binding.etConfirmationPassword.error = "Konfirmasi password tidak boleh kosong"
                return@setOnClickListener
            }

            viewModel.registerUser(
                RegisterBody(
                    username,
                    email,
                    phoneNumber,
                    password,
                    confirmationPassword
                )
            )
        }
    }

    private fun initSignInButton() {
        binding.tvSignInBtn.setOnClickListener {
            startDynamicActivity(this, SignInActivity::class.java)
        }
    }

    companion object {
        private const val EMAIL_TO_REGISTER = "EMAIL_TO_REGISTER"
        private const val OTP_CODE = "OTP_CODE"
    }
}