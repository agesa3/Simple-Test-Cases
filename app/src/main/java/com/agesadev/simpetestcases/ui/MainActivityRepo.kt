package com.agesadev.simpetestcases.ui

import com.agesadev.simpetestcases.api.ApiResponse
import com.agesadev.simpetestcases.api.ApiService
import com.agesadev.simpetestcases.local.AppDatabase
import com.agesadev.simpetestcases.model.UserBody
import retrofit2.Response

class MainActivityRepo(val apiService: ApiService, val localDb: AppDatabase) {

    suspend fun createNewUser(userBody: UserBody): Response<ApiResponse> {
        return apiService.createUser(userBody = userBody)
    }

    suspend fun insertUserToDb(userBody: UserBody) {
        localDb.userDao().insert(userBody)
    }

    suspend fun updateUserLocal(userBody: UserBody) {
        localDb.userDao().update(userBody)
    }

    suspend fun deleteUserFromDb(userBody: UserBody) {
        localDb.userDao().delete(userBody = userBody)
    }

    suspend fun getAllUsersFromDb(): List<UserBody> {
        return localDb.userDao().getAll()
    }
}