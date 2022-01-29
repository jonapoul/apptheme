package com.jonapoul.theme.sample

import androidx.multidex.MultiDexApplication
import com.jonapoul.theme.AppTheme

class SampleApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AppTheme.init(context = this, customSharedPrefsName = null)
    }
}
