package com.example.nav

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Profile::class],version=1)
 abstract class ProfileDB: RoomDatabase()
{
  abstract fun ProfileDAO():ProfileDAO
 companion object {

  @Volatile
  private var INSTANCE: ProfileDB? = null


  fun getDB(context: Context): ProfileDB {
   if (INSTANCE == null) {
    synchronized(this) {
     INSTANCE = Room.databaseBuilder(
      context.applicationContext,
      ProfileDB::class.java, "ContactDataBase"
     ).build()
    }
   }
   return INSTANCE!!

  }

 }
 }