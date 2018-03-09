package com.complexrecycler.module.home.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.complexrecycler.R
import com.complexrecycler.base.BaseActivity
import com.complexrecycler.constants.ApiConstants
import com.complexrecycler.model.User
import com.complexrecycler.module.home.adapter.HomeAdapter
import com.complexrecycler.module.home.presenter.HomePresenter
import com.complexrecycler.module.home.target.HomeTarget
import com.complexrecycler.utils.AppUtils
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.annotations.NotNull
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Saveen on 08/03/18.
 */
class HomeActivity : BaseActivity(), HomeTarget, SwipeRefreshLayout.OnRefreshListener {

    lateinit @Inject var presenter: HomePresenter
    lateinit @Inject var mAppUtils: AppUtils

    lateinit var mLinearLayoutManager: LinearLayoutManager

    internal var scheduledRidesList = ArrayList<User>()
    private var offset = 0
    private var hasMore = false
    lateinit var mAdapter : HomeAdapter
    private var hasMoreApiData = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)
        presenter.takeTarget(this)

        mAdapter = HomeAdapter(scheduledRidesList, this )

        usersSwipeRefreshLayout.setOnRefreshListener(this)

        usersList.setHasFixedSize(true)
        mLinearLayoutManager = LinearLayoutManager(this)
        usersList.layoutManager = mLinearLayoutManager

        usersList.addOnScrollListener(mRecyclerViewOnScrollListener)

        usersList.adapter = mAdapter

        if (mAppUtils.isOnline(usersList)) {
            presenter.getHomeList(offset)
        }
    }

    companion object {

        @JvmStatic
        fun createIntent(@NotNull context: Context): Intent {

            val intent = Intent(context, HomeActivity::class.java)
            return intent
        }

    }

    override val layout: Int
        get() = R.layout.activity_home


    override fun onDestroy() {
        super.onDestroy()
        presenter.dropTarget()
    }

    override fun showHomeList(userList : ArrayList<User>, hasMore : Boolean) {

        if (userList.size == 0) {
            hideList()
            return
        }
        this.hasMoreApiData = hasMore
        scheduledRidesList.addAll(userList)
        mAdapter.notifyDataSetChanged()

        stopRefreshing()

    }


    //on scroll listener for recycler view. Pagination logic works here
    private val mRecyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (hasMore && mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.itemCount - 1 ) {
                offset += ApiConstants.PAGINATION_LIMIT

                if (hasMoreApiData){
                    if (mAppUtils.isOnline(usersList)) {
                        presenter.getHomeList(offset)
                    }
                    hasMore = false
                }else{

                }

            }
        }
    }


    override fun stopPagination() {
        hasMore = false
    }

    override fun hasMoreData() {
        hasMore = true
    }


    override fun onRefresh() {

        startRefreshing()

        scheduledRidesList.clear()
        mAdapter.notifyDataSetChanged()
        hasMore = false
        offset = 0
        if (mAppUtils.isOnline(usersList)) {
            presenter.getHomeList(offset)
        }
    }

    override fun hideList() {
        tvNoItemFound.visibility = View.VISIBLE
        usersList.visibility = View.GONE

        stopRefreshing()
    }

    override fun showList() {
        tvNoItemFound.visibility = View.GONE
        usersList.visibility = View.VISIBLE

        stopRefreshing()
    }

    /**
     * start refreshing
     */
    private fun startRefreshing() {
        usersSwipeRefreshLayout.isEnabled = false
        usersSwipeRefreshLayout.isRefreshing = true
    }

    /**
     * stop refreshing
     */
    private fun stopRefreshing() {
        usersSwipeRefreshLayout.isEnabled = true
        if (usersSwipeRefreshLayout.isRefreshing)
            usersSwipeRefreshLayout.isRefreshing = false
    }

}