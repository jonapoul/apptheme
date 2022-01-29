package com.jonapoul.theme.sample

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import com.jonapoul.extensions.navigation.safelyNavigate
import com.jonapoul.theme.AppTheme

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val nav = findNavController()
        when (item.itemId) {
            R.id.action_light -> AppTheme.set(requireContext(), AppTheme.LIGHT)
            R.id.action_dark -> AppTheme.set(requireContext(), AppTheme.DARK)
            R.id.action_about -> nav.safelyNavigate(SettingsFragmentDirections.toAbout())
        }
        refreshAllPreferences()
        return super.onOptionsItemSelected(item)
    }

    private fun refreshAllPreferences() {
        preferenceScreen = null
        addPreferencesFromResource(R.xml.settings)
    }
}
