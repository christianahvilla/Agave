package com.christianahvila.agave.ui.home

import com.christianahvila.agave.api.ApiServiceInterface
import com.christianahvila.agave.models.Beer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter: HomeContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private lateinit var view: HomeContract.View
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    override fun loadData(page: Int) {
        val subscribe = api.getBeers(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ beers: ArrayList<Beer>? ->
                view.showProgress(false)
                beers?.let { view.loadDataSuccess(it) }
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

}