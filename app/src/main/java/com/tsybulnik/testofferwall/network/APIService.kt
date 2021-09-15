package com.tsybulnik.testofferwall.network

import com.tsybulnik.testofferwall.model.DataList
import com.tsybulnik.testofferwall.model.Obj
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {
    @GET("entities/getAllIds")
    fun getDataList(): Call<DataList>

    @GET("object/{type}")
    fun getView(@Path("type") type:Any): Call<Obj>
}