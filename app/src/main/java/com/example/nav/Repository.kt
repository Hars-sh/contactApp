package com.example.nav

import androidx.lifecycle.LiveData

class Repository(private val profileDAO: ProfileDAO) {
    suspend fun insertUser(profile: Profile) {
        profileDAO.insertUser(profile)
    }

    suspend fun updateUser(profile: Profile) {
        profileDAO.updateUser(profile)
    }

    suspend fun deleteUser(profile: Profile) {
        profileDAO.deleteUser(profile)
    }

    fun getUser(id: Long): LiveData<Profile> {
        return profileDAO.getUser(id)
    }

    fun mailExist(email: String): LiveData<Int> {
        return profileDAO.mailExist(email)
    }

    fun userExist(email: String, fname: String): LiveData<Int> {
        return profileDAO.userExist(email, fname)
    }

    fun getId(email: String): LiveData<Long> {
        return profileDAO.getId(email)
    }
}