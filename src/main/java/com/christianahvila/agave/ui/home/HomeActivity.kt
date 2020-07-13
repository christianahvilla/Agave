package com.christianahvila.agave.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christianahvila.agave.R
import com.christianahvila.agave.di.component.DaggerActivityComponent
import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.utils.CommonFunctions

class HomeActivity : AppCompatActivity(), HomeContract.View, CommonFunctions {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this@HomeActivity))
            .build()
        activityComponent.inject(this@HomeActivity)
    }
}