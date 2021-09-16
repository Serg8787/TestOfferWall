package com.tsybulnik.testofferwall.network

import com.google.gson.JsonObject
import com.tsybulnik.testofferwall.model.DataList
import com.tsybulnik.testofferwall.model.Obj
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*


interface APIService {
    @GET("entities/getAllIds")
    fun getDataList(): Call<DataList>

    @GET("object/{id}")
    fun getView(@Path("id") id:Int): Call<Any>
}