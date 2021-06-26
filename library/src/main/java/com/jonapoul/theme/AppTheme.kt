package com.jonapoul.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.jonapoul.extensions.sharedprefs.PrefPair
import com.jonapoul.extensions.sharedprefs.getStringFromPair

@Suppress("unused")
enum class AppTheme(val string: String, val int: Int) {
    SYSTEM("system", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("light", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("dark", AppCompatDelegate.MODE_NIGHT_YES);

    companion object {
        private val PREF = PrefPair("app_theme", "system")
        internal val key: String = PREF.key

        fun set(context: Context, theme: AppTheme) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit { putString(key, theme.string) }
            AppCompatDelegate.setDefaultNightMode(theme.int)
        }

        fun setFromPrefs(prefs: SharedPreferences) {
            AppCompatDelegate.setDefaultNightMode(
                fromString(
                    prefs.getStringFromPair(PREF)
                )
            )
        }

        fun init(context: Context) {
            setFromPrefs(
                PreferenceManager.getDefaultSharedPreferences(context)
            )
        }

        private fun fromString(str: String): Int = values().firstOrNull { it.string == str }?.int
            ?: error("Unknown theme '$str'")
    }
}
