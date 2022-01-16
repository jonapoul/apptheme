package com.jonapoul.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
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
        fun getPrefs(context: Context): SharedPreferences = context.getSharedPreferences(
            Constants.SHARED_PREFS_NAME,
            Context.MODE_PRIVATE
        )

        /**
         * Persists and applies the given [AppTheme] to the app.
         */
        fun set(context: Context, theme: AppTheme) {
            getPrefs(context).edit { putString(Constants.PREF_KEY, theme.string) }
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
            setFromPrefs(getPrefs(context))
        }

        private fun fromString(str: String): Int = values().firstOrNull { it.string == str }?.int
            ?: error("Unknown theme '$str'")
    }
}
