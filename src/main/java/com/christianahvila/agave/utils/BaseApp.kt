package com.christianahvila.agave.utils

import android.app.Application
import com.christianahvila.agave.di.component.ApplicationComponent
import com.christianahvila.agave.di.component.DaggerApplicationComponent
import com.christianahvila.agave.di.module.ApplicationModule

class BaseApp: Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

    }

    private fun setup() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}