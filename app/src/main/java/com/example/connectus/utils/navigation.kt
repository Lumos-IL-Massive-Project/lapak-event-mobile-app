package com.example.connectus.utils

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import com.example.connectus.R

fun startDynamicActivity(context: Context, activityClass: Class<*>) {
    val intent = Intent(context, activityClass).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
    val options = ActivityOptions.makeCustomAnimation(
        context,
        R.anim.slide_in,
        R.anim.slide_out
    )
    context.startActivity(intent, options.toBundle())
}
