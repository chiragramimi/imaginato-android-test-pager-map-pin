package com.imaginato.randomusers.data.randomuser.repository

import com.imaginato.randomusers.data.randomuser.entity.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Random User API
 */
interface RandomUserApi {

    /**
     * @param perPageCount Number of users will get on one API call
     * @return RandomUserResponse will get
     * Random user API end point to get number of users
     */
    @GET("/api")
    suspend fun fetchRandomUsers(@Query("results") perPageCount: Int): RandomUserResponse
}
