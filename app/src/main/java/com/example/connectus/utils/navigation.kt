package com.example.connectus.utils

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import com.example.connectus.R

fun startDynamicActivity(
    context: Context,
    activityClass: Class<*>,
    animIn: Int = R.anim.slide_left_in,
    animOut: Int = R.anim.slide_left_out,
    vararg data: Pair<String, Any?>
) {
    val intent = Intent(context, activityClass).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        data.forEach { (key, value) ->
            when (value) {
                is String -> putExtra(key, value)
                is Int -> putExtra(key, value)
                // add more
            }
        }
    }
    val options = ActivityOptions.makeCustomAnimation(
        context, animIn, animOut
    )
    context.startActivity(intent, options.toBundle())
}
