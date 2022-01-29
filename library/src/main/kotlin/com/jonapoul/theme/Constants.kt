package com.jonapoul.theme

import com.jonapoul.extensions.sharedprefs.PrefPair

internal object Constants {
    const val SHARED_PREFS_NAME = "AppTheme"

    const val PREF_KEY = "app_theme"
    val PREF_PAIR = PrefPair(PREF_KEY, AppTheme.SYSTEM.string)
}
