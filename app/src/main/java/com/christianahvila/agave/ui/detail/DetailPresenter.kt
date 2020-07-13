package com.christianahvila.agave.ui.detail

import com.christianahvila.agave.api.ApiServiceInterface
import com.christianahvila.agave.models.BeerDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter: DetailContract.Presenter {
    private lateinit var view: DetailContract.View
    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()


    override fun loadData(id: Int) {
        val subscribe = api.getDetailBeer(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ detailed: ArrayList<BeerDetail> ->
                view.loadDataSuccess(detailed[0])
            }, { 
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

}