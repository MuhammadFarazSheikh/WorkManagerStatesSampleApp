package com.montymobile.interfaces

import com.montymobile.callsignature.networking.ApiEndPoints
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface
{
    @GET(ApiEndPoints.SEARCH_WEATHER_BY_NAME)
    fun getData(@Query("q") name:String,@Query("APPID") appId:String):Call<Any>
}