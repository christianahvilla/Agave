package com.christianahvila.agave.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.christianahvila.agave.R
import com.christianahvila.agave.models.Beer
import com.christianahvila.agave.utils.BaseViewHolder
import com.christianahvila.agave.utils.Constants
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class HomeAdapter(private val beers: ArrayList<Beer>, val context: Context): RecyclerView.Adapter<BaseViewHolder>() {

    private var isLoaderVisible = false

    private val listener: OnItemClickListener

    init {
        this.listener = context as OnItemClickListener
    }

    override fun getItemCount(): Int {
        return beers.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            Constants.VIEW_TYPE_NORMAL -> HomeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_row_beer, parent, false)
            )
            Constants.VIEW_TYPE_LOADING -> ProgressHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_row_loading, parent, false)
            )
            else -> ProgressHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_row_loading, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == beers.size - 1) Constants.VIEW_TYPE_LOADING else Constants.VIEW_TYPE_NORMAL
        } else {
            Constants.VIEW_TYPE_NORMAL
        }
    }

    fun addItems(nextBeers: ArrayList<Beer>) {
        if (beers.isNotEmpty())
            removeLoading()
        beers.addAll(nextBeers)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        beers.add(Beer(
            0,
            "",
            "",
            ""
        ))
        notifyItemInserted(beers.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position: Int = beers.size - 1
        val item: Beer = getItem(position)
        if (item.id == 0) {
            beers.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        beers.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Beer {
        return beers[position]
    }

    inner class HomeViewHolder(itemView: View): BaseViewHolder(itemView) {
        private val beerImage: ImageView = itemView.findViewById(R.id.iv_row_image)
        private val beerLayout: ConstraintLayout = itemView.findViewById(R.id.cl_row_layout)
        private val beerName: TextView = itemView.findViewById(R.id.tv_row_name)
        private val beerTagLine: TextView = itemView.findViewById(R.id.tv_row_name)

        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)
            Picasso.get().load(beers[position].imageURL).fit().centerCrop().transform(
                RoundedCornersTransformation(10, 0)
            ).into(beerImage)
            beerName.text = beers[position].name
            beerTagLine.text = beers[position].tagLine

            beerLayout.setOnClickListener {
                listener.itemDetail(beers[position].id)
            }
        }
    }

    inner class ProgressHolder (itemView: View) : BaseViewHolder(itemView) {
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

        override fun clear() {}
    }

    interface OnItemClickListener {
        fun itemDetail(id: Int)
    }
}