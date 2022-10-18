package net.testportal.suitmedia.data.remote

import net.testportal.suitmedia.data.remote.ReqresinApiClient.PER_PAGE
import net.testportal.suitmedia.data.remote.responses.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresinApiService {

    @GET("users")
    suspend fun fetchUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = PER_PAGE,
    ): ResponseDto
}
