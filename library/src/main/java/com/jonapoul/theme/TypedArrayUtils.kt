package com.jonapoul.theme

import android.content.Context
import android.util.TypedValue

/**
 * Copied each of these methods from from androidx.core.content.res.TypedArrayUtils, since for some
 * godforsaken reason us plebs are blocked from accessing them.
 */
internal fun getAttr(context: Context, attr: Int, fallbackAttr: Int): Int {
    val value = TypedValue()
    context.theme.resolveAttribute(attr, value, true)
    return if (value.resourceId != 0) attr else fallbackAttr
}

