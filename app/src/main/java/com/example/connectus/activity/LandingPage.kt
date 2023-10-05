package com.example.connectus.activity

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.google.android.material.button.MaterialButton


class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object: AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                val signInButton = findViewById<MaterialButton>(R.id.signInButton)
                signInButton.visibility = View.VISIBLE
                signInButton.startAnimation(fadeInAnimation)

                val signUpButton = findViewById<MaterialButton>(R.id.signUpButton)
                signUpButton.visibility = View.VISIBLE
                signUpButton.startAnimation(fadeInAnimation)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        val logo = findViewById<ImageView>(R.id.logo)
        logo.startAnimation(slideUpAnimation)
    }

    fun handleSignIn(view: View?) {

    }

    fun handleSignUp(view: View?) {

    }
}