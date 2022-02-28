package com.jonapoul.theme

import com.jonapoul.common.data.PrefPair

internal object Constants {
    const val PREF_KEY = "app_theme"
    val PREF_PAIR = PrefPair(PREF_KEY, AppTheme.SYSTEM.string)
}
