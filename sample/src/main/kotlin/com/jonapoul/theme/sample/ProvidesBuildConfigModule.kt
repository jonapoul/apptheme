package com.jonapoul.theme.sample

import com.jonapoul.common.core.IBuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.threeten.bp.Instant

@InstallIn(SingletonComponent::class)
@Module
class ProvidesBuildConfigModule {
    @Provides
    fun provides(): IBuildConfig = object : IBuildConfig {
        override val debug = BuildConfig.DEBUG
        override val versionName = BuildConfig.VERSION_NAME
        override val versionCode = BuildConfig.VERSION_CODE
        override val gitId = ""
        override val buildTime = Instant.ofEpochMilli(BuildConfig.BUILD_TIME_MS)
    }
}
