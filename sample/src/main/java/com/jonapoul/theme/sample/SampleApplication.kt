package com.jonapoul.theme.sample

import android.app.Application
import com.jonapoul.theme.AppTheme

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppTheme.init(this)
    }
}
