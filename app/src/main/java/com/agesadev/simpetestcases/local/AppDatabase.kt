package com.agesadev.simpetestcases.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agesadev.simpetestcases.model.UserBody

@Database(entities = [UserBody::class], version = 1)
abstract class AppDatabase :RoomDatabase(){

    abstract fun userDao():UserDao
}