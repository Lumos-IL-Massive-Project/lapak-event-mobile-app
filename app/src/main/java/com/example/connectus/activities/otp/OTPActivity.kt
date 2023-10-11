package com.example.connectus.activities.otp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivityOtpBinding

class OTPActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmBtn.setOnClickListener(this)
        binding.tvSignInBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.confirmBtn -> {
                handleConfirmOTP()
            }

            R.id.tvSignInBtn -> {
                startActivity(Intent(this, SignInActivity::class.java))
            }
        }
    }

    private fun handleConfirmOTP() {
        startActivity(Intent(this, SignInActivity::class.java))
    }
}