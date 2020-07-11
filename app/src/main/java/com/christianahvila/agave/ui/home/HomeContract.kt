package com.christianahvila.agave.ui.home

import com.christianahvila.agave.ui.BaseContract

class HomeContract {

    interface View: BaseContract.View{
    }

    interface Presenter: BaseContract.Presenter<View> {
    }

}