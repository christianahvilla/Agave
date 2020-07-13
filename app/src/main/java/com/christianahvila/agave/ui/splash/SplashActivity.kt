package com.christianahvila.agave.ui.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.christianahvila.agave.R
import com.christianahvila.agave.di.component.DaggerActivityComponent
import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.ui.home.HomeActivity
import com.christianahvila.agave.utils.CommonFunctions
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : Activity(), CommonFunctions, SplashContract.View {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnim()
    }

    override fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this@SplashActivity))
            .build()
        activityComponent.inject(this@SplashActivity)
    }

    override fun startAnim() {
        val monitor = Runnable {
            mHandler.postDelayed(openWelcome(this@SplashActivity),2500)
        }

        val ani = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        iv_splash_image.startAnimation(ani)
        monitor.run()
    }

    private fun openWelcome(context: Context): Runnable {
        return Runnable{
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            finish()
        }
    }
}