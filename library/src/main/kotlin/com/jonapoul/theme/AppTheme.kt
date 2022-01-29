package com.jonapoul.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.jonapoul.extensions.sharedprefs.getStringFromPair

/**
 * An enum class to hold the three possible app theme states: light, dark and system.
 */
enum class AppTheme(val string: String, val int: Int) {
    SYSTEM("system", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("light", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("dark", AppCompatDelegate.MODE_NIGHT_YES);

    companion object {
        private var sharedPrefsName: String? = null

        internal fun getPrefs(context: Context): SharedPreferences {
            return when (sharedPrefsName) {
                null -> PreferenceManager.getDefaultSharedPreferences(context)
                else -> context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE)
            }
        }

        /**
         * Persists and applies the given [AppTheme] to the app.
         */
        fun set(context: Context, theme: AppTheme) {
            getPrefs(context).edit { putString(Constants.PREF_KEY, theme.string) }
            AppCompatDelegate.setDefaultNightMode(theme.int)
        }

        /**
         * Attempts to get the saved app theme from the app's [SharedPreferences], then applies it.
         */
        fun setFromPrefs(prefs: SharedPreferences) {
            AppCompatDelegate.setDefaultNightMode(
                fromString(
                    prefs.getStringFromPair(Constants.PREF_PAIR)
                )
            )
        }

        /**
         * Should be called in the Application or Activity's onCreate method. Sets the initial theme
         * state based on whatever was persisted before, defaulting to "follow system".
         */
        fun init(context: Context, customSharedPrefsName: String? = null) {
            sharedPrefsName = customSharedPrefsName
            setFromPrefs(getPrefs(context))
        }

        /**
         * Returns the [AppCompatDelegate] constant integer corresponding to the given [str] value.
         * [str] should take one of the values "system", "light" or "dark" (case-sensitive) - or
         * else an [IllegalStateException] is thrown.
         */
        private fun fromString(str: String): Int = values()
            .firstOrNull { it.string == str }
            ?.int
            ?: error("Unknown theme '$str'")
    }
}
