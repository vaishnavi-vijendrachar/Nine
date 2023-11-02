package com.vaishnavi.repository

import com.vaishnavi.model.Response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val URL = "https://bruce-v2-mob.fairfaxmedia.com.au/"

class ApiClient {

    private fun getRetrofitInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val buildService: RemoteService = getRetrofitInstance()
        .create(RemoteService::class.java)
}

interface RemoteService {
    @GET("1/alfred_live/67184313/offline/afr")
    suspend fun getDataFromRemote() : Response
}
