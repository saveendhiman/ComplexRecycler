package com.complexrecycler.module.home.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.complexrecycler.R
import com.complexrecycler.base.ComplexRecyclerApplication
import com.complexrecycler.model.User
import com.complexrecycler.utils.ImageUtility
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Saveen on 08/03/18.
 */

class HomeAdapter(val headerlist: ArrayList<User>, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit @Inject var mImageUtility: ImageUtility
//    private var spacingDecoration: GridSpacingItemDecoration? = null

    override fun getItemCount(): Int {
        return headerlist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        val arrayList = headerlist[position]
        holder as PopularViewHolder

        mImageUtility.loadImage(arrayList.image, holder.ivUserProfileImage!!, R.drawable.ic_launcher_background, true )
        holder.tvUserName!!.text = arrayList.name

        val spanCount = 2
//        val spacingDecoration = GridSpacingItemDecoration(spanCount, Measure.pxToDp(10, context), true)

        holder.horizontalList!!.setHasFixedSize(true)
//        holder.horizontalList!!.addItemDecoration(spacingDecoration)

        holder.horizontalList!!.layoutManager = GridLayoutManager(context, spanCount)

        val mAdapter = HomeAdapterHorizontal(arrayList.items, context)
        val mLayoutManager = GridLayoutManager(context, 2)
        mLayoutManager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position:Int):Int {
                when (mAdapter.getItemViewType(position)) {
                    HomeAdapterHorizontal.VIEW_TYPE_HEADER -> return 2
                    HomeAdapterHorizontal.VIEW_TYPE_NORMAL -> return 1
                    else -> return -1
                }
            }
        }
        holder.horizontalList!!.setLayoutManager(mLayoutManager)

        holder.horizontalList!!.adapter = mAdapter

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_headerlist, parent, false)
        return PopularViewHolder(view)
    }

    class PopularViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var ivUserProfileImage: ImageView? = null
        var tvUserName: TextView? = null
        var horizontalList: RecyclerView? = null
        var parent: RelativeLayout? = null

        init {
            ivUserProfileImage = item.findViewById(R.id.ivUserProfileImage)
            tvUserName = item.findViewById(R.id.tvUserName)
            horizontalList = item.findViewById(R.id.horizontalList)
            parent = item.findViewById(R.id.rlsearchLocationRoot)

        }
    }

    init {
        (context.applicationContext as ComplexRecyclerApplication).appComponent.inject(this)
    }

}
