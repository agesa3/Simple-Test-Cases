package com.agesadev.simpetestcases.local

import androidx.room.*
import com.agesadev.simpetestcases.model.UserBody

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userBody: UserBody)

    @Delete
    suspend fun delete(userBody: UserBody)

    @Update
    suspend fun update(userBody: UserBody)

    @Query("select * from UserBody")
    suspend fun getAll(): List<UserBody>

}