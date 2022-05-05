package com.jonapoul.theme.sample

import android.view.MenuItem
import com.jonapoul.common.ui.CommonPreferenceFragment
import com.jonapoul.theme.AppTheme

class SettingsFragment : CommonPreferenceFragment(
    settings = R.xml.settings,
    menu = R.menu.menu,
) {
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_light -> AppTheme.set(AppTheme.LIGHT)
            R.id.action_dark -> AppTheme.set(AppTheme.DARK)
        }
        return super.onOptionsItemSelected(item)
    }
}
