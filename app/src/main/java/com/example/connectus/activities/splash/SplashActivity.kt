package com.example.connectus.activities.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.activities.signup.SignUpActivity
import com.example.connectus.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val signInButton = binding.signInButton
        val signUpButton = binding.signUpButton
        val logo = binding.logo

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                signInButton.visibility = View.VISIBLE
                signUpButton.visibility = View.VISIBLE
                signInButton.startAnimation(fadeInAnimation)
                signUpButton.startAnimation(fadeInAnimation)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        logo.startAnimation(slideUpAnimation)

        signInButton.setOnClickListener(this)
        signUpButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.signInButton -> {
                handleSignIn()
            }

            R.id.signUpButton -> {
                handleSignUp()
            }
        }
    }

    private fun handleSignIn() {
        val intent = Intent(this, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val options =
            ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
        startActivity(intent, options.toBundle())
    }

    private fun handleSignUp() {
        val intent = Intent(this, SignUpActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val options =
            ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
        startActivity(intent, options.toBundle())
    }
}