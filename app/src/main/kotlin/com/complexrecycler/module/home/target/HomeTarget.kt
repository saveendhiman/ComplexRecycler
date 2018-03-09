package com.complexrecycler.module.home.target

import com.complexrecycler.model.User
import com.complexrecycler.mvpbase.BaseTarget
import java.util.ArrayList

/**
 * Created by Saveen on 08/03/18.
 */

interface HomeTarget : BaseTarget {

    fun showHomeList(userList : ArrayList<User>, hasMore : Boolean)

    fun hideList()

    fun showList()

    fun stopPagination()

    fun hasMoreData()
}