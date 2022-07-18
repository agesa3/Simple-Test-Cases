package com.agesadev.simpetestcases.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agesadev.simpetestcases.BaseApplication
import com.agesadev.simpetestcases.api.RetrofitObject
import com.agesadev.simpetestcases.model.UserBody
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val db = BaseApplication.INSTANCE.getAppDatabase()
    val mainActivityRepo = MainActivityRepo(RetrofitObject.apiService, db)

    fun createUser(userBody: UserBody) {
        viewModelScope.launch {
            val response = mainActivityRepo.createNewUser(userBody)
            if (response.isSuccessful) mainActivityRepo.insertUserToDb(userBody)
        }
    }
}