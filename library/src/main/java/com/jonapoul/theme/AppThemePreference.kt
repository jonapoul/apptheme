package com.jonapoul.theme

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.util.AttributeSet
import androidx.preference.ListPreference
import androidx.preference.PreferenceManager

class AppThemePreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = getAttr(
        context,
        androidx.preference.R.attr.dialogPreferenceStyle,
        android.R.attr.dialogPreferenceStyle
    ),
    defStyleRes: Int = 0,
) : ListPreference(context, attrs, defStyleAttr, defStyleRes),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    private val shouldShowIcon: Boolean

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AppThemePreference)

        title = a.getText(R.styleable.AppThemePreference_atp_title)
            ?: context.getString(R.string.app_theme)

        shouldShowIcon = a.getBoolean(
            R.styleable.AppThemePreference_atp_shouldShowIcon,
            true
        )

        a.recycle()

        /* Force the default preference key as that bundled in this library */
        key = AppTheme.key

        /* Force our internal entries and entry values to be used */
        setEntryValues(R.array.app_theme_values)
        setEntries(R.array.app_theme_entries)

        /* Set the default value as "follow system". TODO: Change this? */
        setDefaultValue("system")

        /* Force the simple summary provider, meaning that the current theme name will be listed
         * under the preference */
        summaryProvider = SimpleSummaryProvider.getInstance()

        /* Update the displayed icon depending on the current theme. Only does anything if icon
         * space is NOT reserved */
        setThemeIcon()
    }

    override fun onAttached() {
        super.onAttached()
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDetached() {
        super.onDetached()
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == AppTheme.key) {
            AppTheme.setFromPrefs(prefs)
        }
    }

    private fun setThemeIcon() {
        /* Don't change the icon if the user's specified that they don't want any icon space */
        if (!isIconSpaceReserved || !shouldShowIcon) return

        val isDarkTheme = context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        setIcon(
            if (isDarkTheme) {
                R.drawable.ic_theme_dark
            } else {
                R.drawable.ic_theme_light
            }
        )
    }
}
