package com.example.connectus.activities.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.activities.otp.OTPActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivitySignUpBinding
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initForm()
        initSignUpButton()
        initSignInButton()
    }

    private fun initForm() {
        val emailToRegister = intent.getStringExtra(EMAIL_TO_REGISTER)
        binding.etEmail.setText(emailToRegister)
    }

    private fun initSignUpButton() {
        binding.signUpButton.setOnClickListener {
            startDynamicActivity(this, OTPActivity::class.java)
        }
    }

    private fun initSignInButton() {
        binding.tvSignInBtn.setOnClickListener {
            startDynamicActivity(this, SignInActivity::class.java)
        }
    }

    companion object {
        private const val EMAIL_TO_REGISTER = "EMAIL_TO_REGISTER"
    }
}