package com.christianahvila.agave.ui.detail

import com.christianahvila.agave.models.BeerDetail
import com.christianahvila.agave.ui.BaseContract

class DetailContract {

    interface View: BaseContract.View {
        fun loadDataSuccess(detailed: BeerDetail)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(id: Int)
    }
}