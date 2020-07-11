package com.christianahvila.agave.di.module

import android.app.Application
import com.christianahvila.agave.di.scope.PerApplication
import com.christianahvila.agave.utils.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about Application
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class ApplicationModule(private val baseApp: BaseApp) {

    /**
     * Provides the Application
     * @return the baseApp to be provided
     */
    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}