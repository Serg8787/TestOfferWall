package com.tsybulnik.testofferwall.network

import com.tsybulnik.testofferwall.model.DataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("entities/getAllIds")
    fun getDataList(): Call<DataList>

//    @GET("entities/getAllIds")
//    fun getDataList(): Call<Data>
}