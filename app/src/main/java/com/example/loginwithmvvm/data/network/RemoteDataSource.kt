package com.example.loginwithmvvm.data.network

import com.example.loginwithmvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// class for get retrofit client
class RemoteDataSource {

    companion object {

        private const val BASE_URL = "YOUR hosted api"
    }

    // creating retrofit client / build retrofit api
    fun <Api> buildApi(
        api: Class<Api>
    ): Api {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    if(BuildConfig.DEBUG){
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}