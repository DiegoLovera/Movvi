package com.diegolovera.movvi.utils

import android.content.Context

object PreferenceUtils {

    /**
     * Saves the configuration of the theme selected.
     * @param pref boolean with the preference
     */
    fun saveDarkThemeConfig(context: Context, pref: Boolean) {
        val editor = context.getSharedPreferences("THEME_PREFERENCES", Context.MODE_PRIVATE)!!.edit()
        editor.putBoolean("DARK_THEME", pref)
        editor.apply()
    }

    fun loadDarkThemeConfig(context: Context): Boolean {
        val editor = context.getSharedPreferences("THEME_PREFERENCES", Context.MODE_PRIVATE)!!
        return editor.getBoolean("DARK_THEME", false)
    }
}