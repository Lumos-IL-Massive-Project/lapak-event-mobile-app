package com.example.connectus.utils

import java.text.SimpleDateFormat

fun convertTimestamps(value: String): String {
    val sdf = SimpleDateFormat("MM/dd/yyyy")
    return sdf.format(value)
}