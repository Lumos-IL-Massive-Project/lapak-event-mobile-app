package com.example.connectus.utils

import java.text.NumberFormat
import java.util.Locale

fun formatRupiah(number: Int): String? {
    val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return format.format(number)
}