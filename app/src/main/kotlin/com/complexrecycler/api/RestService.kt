package com.complexrecycler.api

import com.complexrecycler.model.HerokuappResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

/**
 *  Created by Saveen on 08/03/18.
 */

interface RestService {

    @GET("/api/users")
    fun homeList(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<Response<HerokuappResponse>>

}