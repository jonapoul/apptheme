package com.jonapoul.theme.sample

import com.jonapoul.extensions.data.IBuildConfig
import com.jonapoul.extensions.data.ms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ProvidesBuildConfigModule {
    @Provides
    fun provides(): IBuildConfig = object : IBuildConfig {
        override val debug = BuildConfig.DEBUG
        override val versionName = BuildConfig.VERSION_NAME
        override val versionCode = BuildConfig.VERSION_CODE
        override val gitId = ""
        override val buildTime = BuildConfig.BUILD_TIME_MS.ms
    }
}
