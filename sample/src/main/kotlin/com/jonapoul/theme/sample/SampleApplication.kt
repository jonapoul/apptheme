package com.jonapoul.theme.sample

import androidx.multidex.MultiDexApplication
import com.jonapoul.extensions.domain.init.AppInitialisers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SampleApplication : MultiDexApplication() {

    @Inject
    lateinit var appInitialisers: AppInitialisers

    override fun onCreate() {
        super.onCreate()

        appInitialisers.init(app = this)
    }
}
