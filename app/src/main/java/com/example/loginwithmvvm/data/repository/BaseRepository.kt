package com.example.loginwithmvvm.data.repository

import com.example.loginwithmvvm.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

// base repository for communicate either with backend Api or or our our local storage --> this repo is for API call
abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall : suspend () -> T) : Resource<T> {  // take suspend function as args

        return withContext(Dispatchers.IO){

            try {

                Resource.Success(apiCall.invoke())

            } catch (throwable: Throwable) {

                when(throwable){

                    is HttpException -> {

                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }

                    else -> {
                        Resource.Failure(true,null, null)
                    }
                }
            }
        }
    }
}