package com.christianahvila.agave.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.christianahvila.agave.R
import com.christianahvila.agave.di.component.DaggerActivityComponent
import com.christianahvila.agave.di.module.ActivityModule
import com.christianahvila.agave.models.BeerDetail
import com.christianahvila.agave.utils.CommonFunctions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_detail.iv_detail_logo
import kotlinx.android.synthetic.main.layout_detail.tv_detail_abv
import kotlinx.android.synthetic.main.layout_detail.tv_detail_description
import kotlinx.android.synthetic.main.layout_detail.tv_detail_fbd
import kotlinx.android.synthetic.main.layout_detail.tv_detail_fp
import kotlinx.android.synthetic.main.layout_detail.tv_detail_ibu
import kotlinx.android.synthetic.main.layout_information_beer.*
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

    override fun loadDataSuccess(detailed: BeerDetail) {
        Picasso.get().load(detailed.imageURL).fit().centerCrop().into(iv_detail_logo)
        tv_detail_description.text = detailed.description
        tv_detail_fbd.text = detailed.firstBrewed
        detailed.foodPairing.iterator()
        title = detailed.name
        var fp = ""
        for (food in detailed.foodPairing) {
            fp = fp + food + "\n"
        }
        tv_detail_fp.text = fp
        tv_information_tag_line.text = detailed.tagLine
        tv_information_beer_name.text = detailed.name
        tv_information_tag_line.text = detailed.tagLine
        tv_detail_abv.text = detailed.abv.toString()
        tv_detail_ibu.text = detailed.ibu.toString()
        convertColor(detailed.srm.toInt())
    }

    override fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this@DetailActivity))
            .build()
        activityComponent.inject(this@DetailActivity)
    }

    @SuppressLint("ResourceType")
    private fun convertColor(color: Int) {
        val srm = resources.obtainTypedArray(R.array.srm)

        when {
            color == 0 -> {
                iv_detail_color.setBackgroundColor(srm.getColor(0, 0))
            }
            color > 40 -> {
                iv_detail_color.setBackgroundColor(srm.getColor(39, 0))
            }
            else -> {
                iv_detail_color.setBackgroundColor(srm.getColor(color - 1, 0))
            }
        }
        srm.recycle()
    }

    private fun initView() {
        presenter.loadData(intent.getIntExtra("id", 0))
    }
}