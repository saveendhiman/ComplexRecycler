package com.complexrecycler.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.DisplayMetrics
import android.view.WindowManager

object Measure {

    val TAG = "Measure"

    fun pxToDp(px: Int, c: Context): Int {
        val displayMetrics = c.resources.displayMetrics
        return Math.round(px * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun dpToPx(dp: Int, context: Context): Float {
        return dp * context.resources.displayMetrics.density
    }

    fun getStatusBarHeight(r: Resources): Int {
        val resourceId = r.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) r.getDimensionPixelSize(resourceId) else 0

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getNavBarHeight(ct: Context): Int {
        return getNavigationBarSize(ct).y
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getNavigationBarSize(context: Context): Point {
        val appUsableSize = getAppUsableScreenSize(context)
        val realScreenSize = getRealScreenSize(context)

        // navigation bar on the right
        if (appUsableSize.x < realScreenSize.x) {
            return Point(realScreenSize.x - appUsableSize.x, appUsableSize.y)
        }

        // navigation bar at the bottom
        return if (appUsableSize.y < realScreenSize.y) {
            Point(appUsableSize.x, realScreenSize.y - appUsableSize.y)
        } else Point()

        // navigation bar is not present
    }

    private fun getAppUsableScreenSize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun getRealScreenSize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getRealSize(size)
        return size
    }

    fun rotateBy(current: Int, degrees: Int): Int {
        // TODO: 21/08/16 a better way should exist
        /*int rotation = current + degrees;
    if (rotation > 359) rotation -=360;
    if (rotation < 0) rotation +=360;*/
        return (current + degrees) % 360
    }
}
