package com.example.connectus.activities.signin

import android.app.ActivityOptions
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
                val intent = Intent(this, SignUpActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
                val options =
                    ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
                startActivity(intent, options.toBundle())
            }
        }
    }

    private fun handleSignIn() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val options =
            ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
        startActivity(intent, options.toBundle())
    }
}