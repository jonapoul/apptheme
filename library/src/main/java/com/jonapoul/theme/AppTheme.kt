package com.jonapoul.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.jonapoul.extensions.sharedprefs.PrefPair
import com.jonapoul.extensions.sharedprefs.getStringFromPair

/**
 * An enum class to hold the three possible app theme states: light, dark and system.
 */
@Suppress("unused")
enum class AppTheme(val string: String, val int: Int) {
    SYSTEM("system", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("light", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("dark", AppCompatDelegate.MODE_NIGHT_YES);

    companion object {
        internal val PREF = PrefPair("app_theme", "system")

        /**
         * Persists and applies the given [AppTheme] to the app.
         */
        fun set(context: Context, theme: AppTheme) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit { putString(PREF.key, theme.string) }
            AppCompatDelegate.setDefaultNightMode(theme.int)
        }

        /**
         *
         */
        fun setFromPrefs(prefs: SharedPreferences) {
            AppCompatDelegate.setDefaultNightMode(
                fromString(
                    prefs.getStringFromPair(PREF)
                )
            )
        }

        /**
         * Should be called in the Application or Activity's onCreate method. Sets the initial theme
         * state based on whatever was persisted before, defaulting to "follow system".
         */
        fun init(context: Context) {
            setFromPrefs(
                PreferenceManager.getDefaultSharedPreferences(context)
            )
        }

        private fun fromString(str: String): Int = values().firstOrNull { it.string == str }?.int
            ?: error("Unknown theme '$str'")
    }
}
