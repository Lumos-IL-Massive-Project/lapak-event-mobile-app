package com.example.connectus.network.bodyrequest

data class RegisterBody(
    val username: String,
    val email: String,
    val phone_number: String,
    val password: String,
    val confirmation_password: String
)
