package com.complexrecycler.module.home.presenter

import com.complexrecycler.constants.ApiConstants
import com.complexrecycler.model.User
import com.complexrecycler.module.home.interactor.HomeData
import com.complexrecycler.module.home.target.HomeTarget
import com.complexrecycler.mvpbase.BasePresenter
import java.util.*
import javax.inject.Inject

/**
 * Created by Saveen on 08/03/18.
 */

class HomePresenter @Inject constructor(private val homeData: HomeData) : BasePresenter<HomeTarget>() {


    fun getHomeList(offset : Int) {

        homeData.executeHomeList(offset, ApiConstants.PAGINATION_LIMIT)
                .doOnSubscribe { target?.showProgressDialog()}
                .doFinally { target?.closeProgressDialog()}
                .subscribe({

                    if (it.data != null){

                        if (it.data.users.isNotEmpty()) {
                            target!!.showList()
                        } else {
                            target!!.hideList()
                        }

                        target!!.showHomeList(it.data.users as ArrayList<User>, it.data.has_more)

                        if (it.data.users.size < ApiConstants.PAGINATION_LIMIT) {
                            target!!.stopPagination()
                        } else {
                            target!!.hasMoreData()
                        }

                    }else{
                        target!!.stopPagination()
                        if (offset == ApiConstants.PAGINATION_LIMIT) {
                            target!!.hideList()
                        }
                    }

                }, {

                    target?.sendErrorToTarget(it)
                })
    }

}