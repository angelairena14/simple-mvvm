package com.example.moneymanager.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log


object SharedPreferencesUtil {
    private fun sharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
    fun setToken(context: Context, token: String){
        val pref = sharedPreferences(context)
        pref.edit().putString("token",token).apply()
    }

    fun getToken(context: Context): String{
        return sharedPreferences(context).getString("token", "") ?: ""
    }
}
