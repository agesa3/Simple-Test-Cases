package com.agesadev.simpetestcases.local

import android.content.Context
import androidx.room.Room

class RoomObject(context: Context) {

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "user_db"
    ).build()
}