package com.example.apirestproject

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "UDB-DSM"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun setJwt(jwt: String) {
        storage.edit().putString("jwt", jwt).apply()
    }

    fun getJwt(): String {
        return storage.getString("jwt", "")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}