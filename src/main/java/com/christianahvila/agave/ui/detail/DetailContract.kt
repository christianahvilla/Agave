package com.christianahvila.agave.ui.detail

import com.christianahvila.agave.models.BeerDetail
import com.christianahvila.agave.ui.BaseContract

class DetailContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(detailed: BeerDetail)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(id: Int)
    }
}