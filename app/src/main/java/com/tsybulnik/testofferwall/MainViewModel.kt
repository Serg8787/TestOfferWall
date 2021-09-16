package com.tsybulnik.testofferwall

import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsybulnik.testofferwall.model.DataList
import com.tsybulnik.testofferwall.model.DataResult
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainViewModel() : ViewModel() {

    val dataResultList = MutableLiveData<List<DataResult>>()

    fun getDada(){
        val retrofit = RetrofitClient.getClient("http://demo3005513.mockable.io/api/v1/").create(APIService::class.java)
        retrofit.getDataList().enqueue(object :retrofit2.Callback<DataList>{
            override fun onResponse(call: Call<DataList>, response: Response<DataList>) {
                Log.d("MyLog",response.body()?.data.toString())

                dataResultList.postValue(response.body()?.data)
            }

            override fun onFailure(call: Call<DataList>, t: Throwable) {

                Log.d("MyLog",t.toString())

            }
        })
    }




}