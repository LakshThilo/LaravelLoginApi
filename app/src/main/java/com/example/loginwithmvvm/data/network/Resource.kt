package com.example.loginwithmvvm.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {

    data class Success<out T>(val value:T) : Resource<T>()

    // these are value that require to handle failure
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}