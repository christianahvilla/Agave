package com.christianahvila.agave.di.component

import com.christianahvila.agave.di.module.ApplicationModule
import com.christianahvila.agave.utils.BaseApp
import dagger.Component

/**
 * Component providing inject() methods for presenters.
 */
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    /**
     * Injects required dependencies into the specified Application.
     * @param application in which to inject the dependencies
     */
    fun inject(application: BaseApp)
}