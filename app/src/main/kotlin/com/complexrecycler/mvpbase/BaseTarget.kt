package com.complexrecycler.mvpbase

import com.complexrecycler.utils.AssignmentException


/**
 * Created by Saveen on 08/03/18.
 */
interface BaseTarget {

    fun showErrorMessage(exception: AssignmentException) {}

    fun showProgressDialog(message: String? = null) {}
    fun closeProgressDialog() {}

    fun sendErrorToTarget(it: Throwable) {
        if (it is AssignmentException)
            showErrorMessage(exception = it)
        else
            showErrorMessage(AssignmentException(-1)) // unknown
    }
}