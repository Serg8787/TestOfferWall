package com.tsybulnik.testofferwall

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.appcompat.app.AppCompatActivity
import com.tsybulnik.testofferwall.fragments.ViewFragment
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val retrofit = RetrofitClient.getClient("http://demo3005513.mockable.io/api/v1/").create(
            APIService::class.java
        )

        val list = retrofit.getDataList().execute().body()?.data
        val idList: ArrayList<Int> = arrayListOf()
        for (number in list!!.indices) {

            idList.add(list[number].id)
        }

        btOn.setOnClickListener {
            val d = idList[0]
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ViewFragment.newInstance(d)).commit()

        }

    }

}