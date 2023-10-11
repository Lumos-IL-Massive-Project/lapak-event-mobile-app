package com.example.connectus.activities.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.signup.SignUpActivity
import com.example.connectus.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener(this)
        binding.tvSignUpBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signInButton -> {
                handleSignIn()
            }

            R.id.tvSignUpBtn -> {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }

    private fun handleSignIn() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}