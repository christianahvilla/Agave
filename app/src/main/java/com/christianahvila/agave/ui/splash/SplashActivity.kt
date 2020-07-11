package com.christianahvila.agave.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christianahvila.agave.R
import com.christianahvila.agave.di.component.DaggerActivityComponent
import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.utils.CommonFunctions

class SplashActivity : AppCompatActivity(), CommonFunctions {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this@SplashActivity))
            .build()
        activityComponent.inject(this@SplashActivity)
    }
}