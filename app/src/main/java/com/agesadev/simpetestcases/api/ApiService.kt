package com.agesadev.simpetestcases.api

import com.agesadev.simpetestcases.model.UserBody
import com.agesadev.simpetestcases.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.USERS_COLLECTION)
    suspend fun createUser(@Body userBody: UserBody): Response<ApiResponse>
}
