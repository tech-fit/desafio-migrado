package com.example.mobiletest.extensions

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreference(val context: Context) {
    private val PREFS_NAME = "etagioMobile"
    private val KEY_NAME = "likes"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    val gson: Gson = Gson()
    val itemType = object : TypeToken<List<Long>>() {}.type

    // Função para dar like ou deslike
    fun like(value: Long) {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        val array: MutableList<Long> = mutableListOf()
        array.addAll(getLikesList())
        val like: Long? = array.find { it == value }
        if (like == null) {
            array.add(value)
        } else {
            array.remove(like)
        }
        val json = gson.toJson(array)
        editor.putString(KEY_NAME, json)
        editor.apply()
    }

    fun getLikesList(): MutableList<Long> {
        val array: MutableList<Long>? =
            gson.fromJson(sharedPref.getString(KEY_NAME, null), itemType)
        if (array == null) {
            return mutableListOf()
        } else {
            return array
        }
    }

    fun isLiked(id: Long): Boolean {
        val array: MutableList<Long> = getLikesList()
        val like: Long? = array.find { it == id }
        return like != null
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.clear()
        editor.apply()
    }
}