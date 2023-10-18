package com.example.connectus.utils

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import com.example.connectus.R

fun startDynamicActivity(
    context: Context,
    activityClass: Class<*>,
    animIn: Int = R.anim.slide_in,
    animOut: Int = R.anim.slide_out
) {
    val intent = Intent(context, activityClass).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
    val options = ActivityOptions.makeCustomAnimation(
        context,
        animIn,
        animOut
    )
    context.startActivity(intent, options.toBundle())
}
