package com.example.authorization

import android.content.Context


class MySharedPreferences(context: Context) {
    val PREFERENCE_NAME = "SharedPreferenceExample"
    val PREFERENCE_EMAIL = "email"
    val PREFERENCE_USERNAME = "name"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getEmail() : String? {
        return preference.getString(PREFERENCE_EMAIL, "")
    }

    fun setEmail(email : String?){
        val editor = preference.edit()
        editor.putString(PREFERENCE_EMAIL, email)
        editor.apply()
    }
    fun getName() : String?{
        return preference.getString(PREFERENCE_USERNAME, "")
    }

    fun setName(name : String?){
        val editor = preference.edit()
        editor.putString(PREFERENCE_USERNAME, name)
        editor.apply()
    }
}