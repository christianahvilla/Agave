package com.christianahvila.agave.di.module

import android.app.Activity
import com.christianahvila.agave.api.ApiServiceInterface
import com.christianahvila.agave.ui.detail.DetailContract
import com.christianahvila.agave.ui.detail.DetailPresenter
import com.christianahvila.agave.ui.home.HomeContract
import com.christianahvila.agave.ui.home.HomePresenter
import dagger.Module
import dagger.Provides


/**
 * Module which provides all required dependencies about Activity
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class ActivityModule(private var activity: Activity) {

    /**
     * Provides the Application Activity
     * @return the Activity to be provided
     */
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    /**
     * Provides the Application HomeActivity
     * @return the HomePresenter to provided
     */
    @Provides
    fun provideHomePresenter(): HomeContract.Presenter {
        return HomePresenter()
    }

    /**
     * Provides the Application HomeActivity
     * @return the HomePresenter to provided
     */
    @Provides
    fun provideDetailedPresenter(): DetailContract.Presenter {
        return DetailPresenter()
    }

    /**
     * Provides the HTTP connections for activity
     * @return the ApiService to provided
     */
    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }

}