package com.example.rocketpunch_interview.data.datasource

import android.content.Context
import android.content.SharedPreferences

class PreferencesService(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun hasValue(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun getString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        sharedPreferences.edit().putString(key, str).apply()
    }
}
