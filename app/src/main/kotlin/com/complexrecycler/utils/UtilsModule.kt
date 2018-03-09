package com.complexrecycler.utils

import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.widget.LinearLayoutManager
import com.complexrecycler.base.ComplexRecyclerApplication
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

/**
 * Created by Saveen on 08/03/18.
 * Provide injects for utility objects
 */

@Module
class UtilsModule(private val mContext: Context) {

    @Provides
    @Singleton
    fun getLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(mContext)
    }

//     get AppUtils instance
    val appUtils: AppUtils
        @Provides
        @Singleton
        get() = AppUtils(mContext as ComplexRecyclerApplication)

    // get DateTimeUtils instance
    val dateTimeUtils: DateTimeUtils
        @Provides
        @Singleton
        get() = DateTimeUtils()

    //get dialog utils
    val dialogUtils: DialogsUtil
        @Provides
        @Singleton
        get() = DialogsUtil()


    //get Preference Manager
    val preferences: PreferenceManager
        @Provides
        @Singleton
        get() = PreferenceManager(mContext)

    //get image utils
    val imageUtils: ImageUtility
        @Provides
        @Singleton
        get() = ImageUtility(mContext)


    @Provides
    @Singleton
    fun providesPackageManger(): PackageManager {
        return mContext.packageManager
    }

    @Provides
    @Singleton
    fun providesAppContext(manager: PackageManager): Context {
        return mContext
    }

}
