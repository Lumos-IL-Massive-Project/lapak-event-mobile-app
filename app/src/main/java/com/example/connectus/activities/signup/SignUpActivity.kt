package com.example.connectus.activities.signup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.otp.OTPActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivitySignUpBinding
import com.example.connectus.utils.startDynamicActivity

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener(this)
        binding.tvSignInBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signUpButton -> {
                handleCreateAccount()
            }

            R.id.tvSignInBtn -> {
                startDynamicActivity(this, SignInActivity::class.java)
            }
        }
    }

    private fun handleCreateAccount() {
        startDynamicActivity(this, OTPActivity::class.java)
    }
}