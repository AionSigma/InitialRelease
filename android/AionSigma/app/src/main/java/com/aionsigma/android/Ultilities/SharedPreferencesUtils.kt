package com.aionsigma.android.Ultilities

import android.app.Activity
import android.content.Context
import com.aionsigma.android.Model.Account.User
import com.google.gson.Gson

object SharedPreferencesUtils {
    private const val AIONSIGMA_APP_SHAREKEY = "AIONSIGMA_APP_SHAREKEY"
    private fun writeString(context: Context, key:String, value:String){
        val sharedPref = context.getSharedPreferences(AIONSIGMA_APP_SHAREKEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(key, value)
            commit()
        }
    }
    private fun readString(context: Context, key:String, defVallue:String = ""):String{
        val sharedPref = context.getSharedPreferences(AIONSIGMA_APP_SHAREKEY,Context.MODE_PRIVATE) ?: null
        return sharedPref!!.getString(key,defVallue)
    }

    public fun clearAll(context: Context){
        val sharedPref = context.getSharedPreferences(AIONSIGMA_APP_SHAREKEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            clear()
            commit()
        }
    }

    private const val USERLOGIN_KEY = "USERLOGIN_KEY"

    fun writeUserLogin(context: Context,user: User?){
        if(user!= null) {
            val gson = Gson()
            writeString(context, USERLOGIN_KEY, gson.toJson(user))
        }
    }
    fun readUserLogin(context: Context):User?{
        val gson = Gson()
        val userJson = readString(context, USERLOGIN_KEY)
        if(!userJson.isNullOrEmpty()) return gson.fromJson(userJson,User::class.java)
        return null
    }
}