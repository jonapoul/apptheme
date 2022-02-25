package com.jonapoul.theme

import android.app.Application
import android.content.SharedPreferences
import com.jonapoul.extensions.domain.init.IAppInitialiser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject

class AppThemeInitialiser @Inject constructor(
    private val prefs: SharedPreferences,
) : IAppInitialiser {
    override fun init(app: Application) {
        AppTheme.init(prefs)
    }
}

@InstallIn(SingletonComponent::class)
@Module
internal interface BindsAppThemeInitialiserModule {
    @Binds
    @IntoSet
    fun initialiser(bind: AppThemeInitialiser): IAppInitialiser
}
