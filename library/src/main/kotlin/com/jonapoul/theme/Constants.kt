package com.jonapoul.theme

import com.jonapoul.common.core.PrefPair

internal object Constants {
    const val PREF_KEY = "app_theme"
    val PREF_PAIR = PrefPair(PREF_KEY, AppTheme.SYSTEM.string)
}
