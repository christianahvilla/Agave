package com.christianahvila.agave.ui.splash

import com.christianahvila.agave.ui.BaseContract

class SplashContract {
    interface View: BaseContract.View{
        fun startAnim()
    }

    interface Presenter: BaseContract.Presenter<View> {
    }
}