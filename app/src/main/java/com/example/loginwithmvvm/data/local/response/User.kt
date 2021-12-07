package com.example.loginwithmvvm.data.local.response

data class User(
    val access_token: String,
    val create_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val updated_at :String
)