package com.example.nav

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDAO {
    @Insert
    suspend fun insertUser(profile:Profile)

    @Update
    suspend fun updateUser(profile:Profile)

    @Delete
    suspend fun deleteUser(profile:Profile)

    @Query("SELECT *FROM CONTACT WHERE id=:id")
    fun getUser(id:Long):LiveData<Profile>

    @Query("SELECT EXISTS(Select *FROM CONTACT WHERE email=:email)")
    fun mailExist(email: String):LiveData<Int>

    @Query("SELECT EXISTS (SELECT * FROM CONTACT WHERE email=:email AND fname=:fname)")
    fun userExist(email: String, fname: String):LiveData<Int>

    @Query("SELECT id FROM Contact WHERE email=:email")
    fun getId(email:String):LiveData<Long>
}