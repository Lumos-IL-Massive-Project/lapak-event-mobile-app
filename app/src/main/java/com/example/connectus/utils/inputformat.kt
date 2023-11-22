package com.example.connectus.utils

fun formatCreditCardNumber(input: String): String {
    val digitsOnly = input.replace("\\D".toRegex(), "")

    val formatted = StringBuilder()
    for (i in digitsOnly.indices) {
        if (i > 0 && i % 4 == 0) {
            formatted.append(" - ")
        }
        formatted.append(digitsOnly[i])
    }

    return formatted.toString()
}

fun formatExpirationDate(input: String): String {
    val digitsOnly = input.replace("\\D".toRegex(), "")

    val formatted = StringBuilder()
    for (i in digitsOnly.indices) {
        if (i > 0 && i % 2 == 0) {
            formatted.append(" / ")
        }
        formatted.append(digitsOnly[i])
    }

    return formatted.toString()
}