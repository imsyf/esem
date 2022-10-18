package net.testportal.suitmedia.data.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ReqresinApiClient {

    private const val baseUrl = "https://reqres.in/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    const val PER_PAGE = 6

    val reqresinApiService: ReqresinApiService by lazy {
        retrofit.create(ReqresinApiService::class.java)
    }
}
