package com.complexrecycler.base

import android.app.Application
import com.complexrecycler.api.NetModule
import com.complexrecycler.utils.UtilsModule
import timber.log.Timber
import com.complexrecycler.BuildConfig

/**
 * Created by Saveen on 08/03/18.
 * Initialization of required libraries
 */
class ComplexRecyclerApplication : Application() {

    lateinit var appComponent: AppComponent private set

    override fun onCreate() {
        super.onCreate()

        //create component
        appComponent = DaggerAppComponent.builder()
                .utilsModule(UtilsModule(this)).netModule(NetModule(this)).build()

        appComponent.inject(this)

        //configure timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
