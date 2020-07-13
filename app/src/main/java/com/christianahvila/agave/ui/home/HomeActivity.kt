package com.christianahvila.agave.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.christianahvila.agave.R
import com.christianahvila.agave.di.component.DaggerActivityComponent
import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.models.Beer
import com.christianahvila.agave.ui.detail.DetailActivity
import com.christianahvila.agave.utils.CommonFunctions
import com.christianahvila.agave.utils.Constants
import com.christianahvila.agave.utils.PaginationListener
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HomeContract.View, CommonFunctions, SwipeRefreshLayout.OnRefreshListener, HomeAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: HomeContract.Presenter
    private var beers: ArrayList<Beer> = ArrayList<Beer>()
    private var adapter: HomeAdapter? = null
    private var currentPage: Int = Constants.PAGE_START
    private var isLastPage = false
    private var totalPage = Constants.PAGE_SIZE
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        injectDependency()
        presenter.attach(this@HomeActivity)
        presenter.subscribe()
        initView()
    }

    private fun initView(){
        presenter.loadData(currentPage)
        sr_home_refresh.isRefreshing = true
        sr_home_refresh.setOnRefreshListener(this@HomeActivity)
        initRecycler()
    }

    override fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this@HomeActivity))
            .build()
        activityComponent.inject(this@HomeActivity)
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this@HomeActivity)
        rv_home_beers_list.layoutManager = layoutManager
        adapter = HomeAdapter(beers, this@HomeActivity)
        rv_home_beers_list.adapter = adapter

        rv_home_beers_list.addOnScrollListener(object : PaginationListener(layoutManager){
            override fun loadMoreItems() {
                isLoading = true
                getMoreBeers()
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    override fun onRefresh() {
        currentPage = Constants.PAGE_START
        isLastPage = false
        adapter!!.clear()
        sr_home_refresh.isRefreshing = true
        presenter.loadData(currentPage)
    }

    override fun showProgress(show: Boolean) {
        sr_home_refresh.isRefreshing = show
        isLoading = false
    }

    override fun showErrorMessage(error: String) {

    }

    override fun loadDataSuccess(beers: ArrayList<Beer>) {
        adapter!!.addItems(beers)
    }

    private fun getMoreBeers() {
        if (currentPage != Constants.PAGE_START) adapter!!.removeLoading()

        if (currentPage <= totalPage) {
            adapter!!.addLoading()
            isLoading = true
            currentPage++
            presenter.loadData(currentPage)
        } else {
            isLastPage = true
        }
    }

    override fun itemDetail(id: Int) {
        val intent = Intent(this@HomeActivity, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}