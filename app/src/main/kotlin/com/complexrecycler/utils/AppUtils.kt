package com.complexrecycler.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.support.design.widget.Snackbar
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.complexrecycler.R
import com.complexrecycler.base.ComplexRecyclerApplication
import com.complexrecycler.constants.ApiConstants
import org.json.JSONObject

import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Created by Saveen on 08/03/18.
 *
 * Contains commonly used methods in an Android App
 */
class AppUtils(private val mContext: ComplexRecyclerApplication) {

    /**
     * Description : Check if user is online or not

     * @return true if online else false
     */
    fun isOnline(v: View): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null && netInfo.isConnectedOrConnecting) {
            return true
        }
        showSnackBar(v, mContext.getString(R.string.toast_network_not_available))
        return false
    }


    /**
     * Description : Hide the soft keyboard

     * @param view : Pass the current view
     */
    fun hideSoftKeyboard(view: View) {
        val inputMethodManager = mContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Description : Hide the soft keyboard

     * @param view : Pass the current view
     */
    fun showSoftKeyboardForce() {

        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hidewSoftKeyboardForce() {

        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }


    /**
     * Show snackbar

     * @param view view clicked
     * *
     * @param text text to be displayed on snackbar
     */
    fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * Show snackbar

     * @param text text to be displayed on Toast
     */
    fun showToast(text: String) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show()
    }


    /**
     * show related error message to user on api failure
     */
    fun showErrorMessage(view: View, t: Throwable) {
        showSnackBar(view, getErrorMessage(t))
    }

    //return error message from webservice error code
    private fun getErrorMessage(throwable: Throwable): String {
        val errorMessage: String
        if (throwable is UnknownHostException || throwable is ConnectException) {
            errorMessage = mContext.resources.getString(R.string.warning_network_error)
        } else {
            errorMessage = "Unfortunately an error has occurred!"
        }
        return errorMessage
    }

    /**
     * Get error message from api's if status code is 400, 401

     * @return error message from response
     */
    fun getErrorMessage(errormsg: String): String {

        var errormessage = ""
        try {
            val jObjError = JSONObject(errormsg)
            errormessage = jObjError.getString(ApiConstants.geterrorMessage)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return errormessage
    }


    /**
     * Open play store
     * @param context
     * *
     * @param appPackageName
     */
    fun openAppInGooglePlay(context: Context, appPackageName: String) {
        //        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)))
        } catch (e: android.content.ActivityNotFoundException) { // if there is no Google Play on device
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)))
        }

    }


    fun getDimensions(context: Context): IntArray {
        val width = context.resources.displayMetrics.widthPixels
        val height = context.resources.displayMetrics.heightPixels
        return intArrayOf(width, height)
    }

    fun getDeviceDensity(context: Context): String {
        var deviceDensity = ""
        when (context.resources.displayMetrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> deviceDensity = 0.75.toString() + " ldpi"
            DisplayMetrics.DENSITY_MEDIUM -> deviceDensity = 1.0.toString() + " mdpi"
            DisplayMetrics.DENSITY_HIGH -> deviceDensity = 1.5.toString() + " hdpi"
            DisplayMetrics.DENSITY_XHIGH -> deviceDensity = 2.0.toString() + " xhdpi"
            DisplayMetrics.DENSITY_XXHIGH -> deviceDensity = 3.0.toString() + " xxhdpi"
            DisplayMetrics.DENSITY_XXXHIGH -> deviceDensity = 4.0.toString() + " xxxhdpi"
            else -> deviceDensity = "Not found"
        }
        return deviceDensity
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun showShortToast(context: Context, message: String, bgColor: Int) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        val view = toast.view
        view.setBackgroundColor(bgColor)
        val text = view.findViewById<View>(android.R.id.message) as TextView
        text.setTextColor(Color.WHITE)
        val leftPadding = context.resources.getDimensionPixelSize(R.dimen.margin_2x)
        val rightPadding = context.resources.getDimensionPixelSize(R.dimen.margin_2x)
        text.setPadding(leftPadding, 0, rightPadding, 0)
        toast.show()
    }


}