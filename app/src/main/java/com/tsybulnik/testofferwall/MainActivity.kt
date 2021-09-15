package com.tsybulnik.testofferwall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tsybulnik.testofferwall.model.DataList
import com.tsybulnik.testofferwall.model.DataResult
import com.tsybulnik.testofferwall.model.Obj
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.getClient(getString(R.string.base_url)).create(APIService::class.java)
        retrofit.getDataList().enqueue(object :retrofit2.Callback<DataList>{
            override fun onResponse(call: Call<DataList>, response: Response<DataList>) {
               Log.d("MyLog",response.body()?.data.toString())
                tvTextHelloWorld.text = response.body()?.data.toString()
                val dataResult: List<DataResult>? = response.body()?.data
            }

            override fun onFailure(call: Call<DataList>, t: Throwable) {
               Toast.makeText(this@MainActivity,""+t.toString(),Toast.LENGTH_LONG).show()
                Log.d("MyLog",t.toString())
                tvTextHelloWorld.text = t.toString()
            }
        })
        val retrofit2 = RetrofitClient.getClient(getString(R.string.base_url)).create(APIService::class.java)
        retrofit.getView(2).enqueue(object :retrofit2.Callback<Obj>{
            override fun onResponse(call: Call<Obj>, response: Response<Obj>) {
                tvTextHelloWorld2.text = response.body()?.type
            }

            override fun onFailure(call: Call<Obj>, t: Throwable) {
                tvTextHelloWorld2.text = t.toString()
            }
        })
    }
}