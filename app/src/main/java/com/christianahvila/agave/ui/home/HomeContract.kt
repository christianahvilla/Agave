package com.christianahvila.agave.ui.home

import com.christianahvila.agave.models.Beer
import com.christianahvila.agave.ui.BaseContract

class HomeContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(beers: ArrayList<Beer>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(page: Int)
    }

}