package com.complexrecycler.module.home.interactor

import com.complexrecycler.api.RestService
import com.complexrecycler.model.HerokuappResponse
import com.complexrecycler.utils.AppUtils
import com.complexrecycler.utils.ErrorResponseTransformer
import com.complexrecycler.utils.PreferenceManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Saveen on 08/03/18.
 */

class HomeData @Inject constructor(val restService: RestService, val mAppUtils: AppUtils, val mPrefs: PreferenceManager) {


    fun executeHomeList(offset : Int ,page : Int): Observable<HerokuappResponse> {

        return restService.homeList(offset,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ErrorResponseTransformer<HerokuappResponse>())
    }

}