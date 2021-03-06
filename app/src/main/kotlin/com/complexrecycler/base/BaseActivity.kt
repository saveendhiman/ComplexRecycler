package com.complexrecycler.base

import android.app.AlertDialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.complexrecycler.utils.AssignmentException
import com.complexrecycler.R
import com.complexrecycler.mvpbase.BaseTarget
import dmax.dialog.SpotsDialog

/**
 * Created by Saveen on 08/03/18.
 * To be extended by all Activities
 */
abstract class BaseActivity : AppCompatActivity(), BaseTarget {

    internal var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    /**
     * get layout to inflate
     */
    @get:LayoutRes
    abstract val layout: Int

    override fun showProgressDialog(message: String?) {
        if (this@BaseActivity == null)
            return

        if (progressDialog == null)
            progressDialog = SpotsDialog(this@BaseActivity, message ?: "Please wait")
        progressDialog!!.setCancelable(false)

        progressDialog!!.show()
    }

    override fun closeProgressDialog() {
        if (progressDialog != null)
            progressDialog!!.dismiss()
    }

    val component: AppComponent
        get() = (this.application as ComplexRecyclerApplication).appComponent

    override fun showErrorMessage(exception: AssignmentException) {
        showGeneralAlert("OK", null, exception.getLocalisedString())
    }

    fun showGeneralAlert(positiveButtonText: String?, negativeButtonText: String?, message: String) {
        val builder = AlertDialog.Builder(this@BaseActivity)
        if (positiveButtonText != null)
            builder.setPositiveButton(positiveButtonText) { dialog, which -> dialog.dismiss() }
        if (negativeButtonText != null)
            builder.setNegativeButton(negativeButtonText) { dialog, which -> dialog.dismiss() }
        builder.setTitle(resources.getString(R.string.app_name))
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.create().show()
    }

}