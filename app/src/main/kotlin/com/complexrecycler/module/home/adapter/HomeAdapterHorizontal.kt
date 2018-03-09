package com.complexrecycler.module.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.complexrecycler.R
import com.complexrecycler.base.ComplexRecyclerApplication
import com.complexrecycler.utils.ImageUtility
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Saveen on 08/03/18.
 */

class HomeAdapterHorizontal(val list: ArrayList<String>, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit @Inject var mImageUtility: ImageUtility
    companion object {
       const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_NORMAL = 2
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when (holder!!.itemViewType) {
            VIEW_TYPE_NORMAL -> {
                val holderItem = holder as ItemViewHolder
                val adapterPosition = holder.getAdapterPosition() - 1

                val arrayList = list[position]
                mImageUtility.loadImage(arrayList, holderItem.ivUserImage!!, R.drawable.ic_launcher_background, false )

            }
            VIEW_TYPE_HEADER -> {

                val holderHeaderItem = holder as ViewHolderHeader
                val arrayList = list[position]
                mImageUtility.loadImage(arrayList, holderHeaderItem.ivUserImageSingle!!, R.drawable.abc_ic_ab_back_material, false )

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            ViewHolderHeader(LayoutInflater.from(parent?.context).inflate(R.layout.item_listsingle, parent, false))
        } else {
            ItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_homelist, parent, false))
        }
    }


    class ItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var ivUserImage: ImageView? = null

        init {
            ivUserImage = item.findViewById(R.id.ivUserImage11)
        }
    }

    class ViewHolderHeader(item: View) : RecyclerView.ViewHolder(item) {

        var ivUserImageSingle: ImageView? = null

        init {
            ivUserImageSingle = item.findViewById(R.id.ivUserImageSingle)
        }
    }


    init {
        (context.applicationContext as ComplexRecyclerApplication).appComponent!!.inject(this)
    }

    override fun getItemViewType(position: Int): Int {
        if ((list.size % 2) == 0) {
            return VIEW_TYPE_NORMAL
        } else {
            return if (position == 0) {
                VIEW_TYPE_HEADER //Let's say that you define that 1 is the view type for the recycler view cell
            } else {
                VIEW_TYPE_NORMAL //And 2 is going to be the "normal" cells
            }
        }
        return VIEW_TYPE_NORMAL
    }

}
