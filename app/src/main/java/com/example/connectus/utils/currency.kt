package com.example.connectus.utils

import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(number: Number?): String? {
    if (number != null) {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(number)
    }

    return ""
}