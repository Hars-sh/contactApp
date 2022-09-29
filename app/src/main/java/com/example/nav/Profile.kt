package com.example.nav

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Contact")
data class Profile(
@PrimaryKey(autoGenerate=true)
val id:Long,
val fname:String="harsh",
  val lname:String,
val phone:String,
@NonNull
val email:String,
val city:String

)