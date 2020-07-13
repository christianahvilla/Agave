package com.christianahvila.agave.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christianahvila.agave.R
import com.christianahvila.agave.di.component.DaggerActivityComponent
import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.models.BeerDetail
import com.christianahvila.agave.utils.CommonFunctions
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.View, CommonFunctions {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        injectDependency()

        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun showProgress(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(error: String) {
        TODO("Not yet implemented")
    }

    override fun loadDataSuccess(detailed: BeerDetail) {
        TODO("Not yet implemented")
    }

    override fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this@DetailActivity))
            .build()
        activityComponent.inject(this@DetailActivity)
    }

    private fun initView() {
        presenter.loadData(intent.getIntExtra("id", 0))
    }
}