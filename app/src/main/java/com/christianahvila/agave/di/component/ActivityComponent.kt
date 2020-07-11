package com.christianahvila.agave.di.component

import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.ui.detail.DetailActivity
import com.christianahvila.agave.ui.home.HomeActivity
import com.christianahvila.agave.ui.splash.SplashActivity
import dagger.Component

/**
 * Component providing inject() methods for presenters.
 */
@Component(modules = [(ActivityModule::class)])
interface ActivityComponent {

    /**
     * Injects required dependencies into the specified Activity.
     * @param detailedActivity in which to inject the dependencies
     */
    fun inject(detailedActivity: DetailActivity)

    /**
     * Injects required dependencies into the specified Activity.
     * @param homeActivity in which to inject the dependencies
     */
    fun inject(homeActivity: HomeActivity)

    /**
     * Injects required dependencies into the specified Activity.
     * @param splashActivity in which to inject the dependencies
     */
    fun inject(splashActivity: SplashActivity)
}