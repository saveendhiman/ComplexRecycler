package com.complexrecycler.base

import com.complexrecycler.api.NetModule
import com.complexrecycler.module.home.view.HomeActivity
import com.complexrecycler.module.home.adapter.HomeAdapter
import com.complexrecycler.module.home.adapter.HomeAdapterHorizontal
import com.complexrecycler.utils.UtilsModule
import javax.inject.Singleton
import dagger.Component

/**
 * Created by Saveen on 08/03/18.
 * Injections for Application class
 */

@Singleton
@Component(modules = arrayOf(UtilsModule::class, NetModule::class))
interface AppComponent {

    fun inject(baseApp: ComplexRecyclerApplication)

    fun inject(homeActivity : HomeActivity)

    fun inject(popularAdapter : HomeAdapter)

    fun inject(homeAdapterHorizontal : HomeAdapterHorizontal)

}