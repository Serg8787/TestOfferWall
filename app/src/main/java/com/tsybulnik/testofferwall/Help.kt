package com.tsybulnik.testofferwall

import android.os.StrictMode
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient

class Help {
//    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//    StrictMode.setThreadPolicy(policy)
//
//    val retrofit = RetrofitClient.getClient("http://demo3005513.mockable.io/api/v1/").create(
//        APIService::class.java
//    )
//
//    val list = retrofit.getDataList().execute().body()?.data
//    val idList: ArrayList<Int> = arrayListOf()
//
//    for (number in list!!.indices) {
//        idList.add(list[number].id)
//    }
//
//    val objOfView = retrofit.getView(idList[0]).execute().body()
//    Log.d("MyLog", objOfView.toString())
//
//    var str: String = ""
//    var viewElement: String = ""
//
//    // текствью
//    if (objOfView.toString().contains("text")) {
//        viewElement = "text"
//        str = objOfView.toString().substring(
//            (objOfView.toString().lastIndexOf("=") + 1),
//            objOfView.toString().length - 1
//        )
//    }
//    // вебвью
//    if (objOfView.toString().contains("webview")) {
//        viewElement = "webView"
//        str = objOfView.toString().substring(
//            (objOfView.toString().lastIndexOf("=") + 1),
//            objOfView.toString().length - 1
//        )
//    }
//    // Картинка
//    if (objOfView.toString().contains("image")) {
//        viewElement = "image"
//        str = objOfView.toString().substring(
//            (objOfView.toString().lastIndexOf("=") + 1),
//            objOfView.toString().length - 1
//        )
//    }
//
//    Log.d("MyLog", viewElement+" "+str)
}