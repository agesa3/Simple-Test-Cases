package com.agesadev.simpetestcases

import android.app.Application
import com.agesadev.simpetestcases.local.AppDatabase
import com.agesadev.simpetestcases.local.RoomObject

class BaseApplication : Application() {

    companion object {
        lateinit var INSTANCE: BaseApplication
    }

    lateinit var roomObject: RoomObject

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        roomObject = RoomObject(this)
    }

    fun getAppDatabase(): AppDatabase {
        return roomObject.db
    }
}