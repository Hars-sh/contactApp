package com.example.nav


import android.content.Context
import android.content.SharedPreferences
import com.example.nav.Constants.DEFAULT_TOKEN
import com.example.nav.Constants.LOGINMANAGE_PREF_FILE
import com.example.nav.Constants.USER_TOKEN

class LogInStateManager(val context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences(LOGINMANAGE_PREF_FILE, Context.MODE_PRIVATE)

    fun saveToken(token: Long) {
        val editor = prefs.edit()
        editor.putLong(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): Long{
        return prefs.getLong(USER_TOKEN, DEFAULT_TOKEN)
    }
}