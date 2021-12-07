package com.example.loginwithmvvm.data.repository

import com.example.loginwithmvvm.data.network.AuthApi

// this repo is for login and sign up
class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(email: String, password: String) =

        safeApiCall { api.login(email, password) }


}