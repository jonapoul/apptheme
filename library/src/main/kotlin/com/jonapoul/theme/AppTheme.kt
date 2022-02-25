package com.jonapoul.theme

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.jonapoul.extensions.domain.getString

/**
 * An enum class to hold the three possible app theme states: light, dark and system.
 */
enum class AppTheme(val string: String, val int: Int) {
    SYSTEM("system", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("light", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("dark", AppCompatDelegate.MODE_NIGHT_YES);

    companion object {
        internal var sharedPrefs: SharedPreferences? = null

        /**
         * Persists and applies the given [AppTheme] to the app.
         */
        fun set(theme: AppTheme) {
            sharedPrefs?.edit { putString(Constants.PREF_KEY, theme.string) }
            AppCompatDelegate.setDefaultNightMode(theme.int)
        }

        /**
         * Attempts to get the saved app theme from the app's [SharedPreferences], then applies it.
         */
        fun setFromPrefs() {
            AppCompatDelegate.setDefaultNightMode(
                fromString(
                    sharedPrefs?.getString(Constants.PREF_PAIR)
                )
            )
        }

        /**
         * Should be called in the Application or Activity's onCreate method. Sets the initial theme
         * state based on whatever was persisted before, defaulting to "follow system".
         */
        fun init(prefs: SharedPreferences) {
            sharedPrefs = prefs
            setFromPrefs()
        }

        /**
         * Returns the [AppCompatDelegate] constant integer corresponding to the given [str] value.
         * [str] should take one of the values "system", "light" or "dark" (case-sensitive) - or
         * else an [IllegalStateException] is thrown.
         */
        private fun fromString(str: String?): Int = values()
            .firstOrNull { it.string == str }
            ?.int
            ?: error("Unknown theme '$str'")
    }
}
