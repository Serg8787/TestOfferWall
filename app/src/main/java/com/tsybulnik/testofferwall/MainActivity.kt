package com.tsybulnik.testofferwall

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tsybulnik.testofferwall.fragments.MainFragment
import com.tsybulnik.testofferwall.fragments.ViewFragment
import com.tsybulnik.testofferwall.network.APIService
import com.tsybulnik.testofferwall.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isFirst: Boolean = true
    private val dataViewModel:DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        openFrag(MainFragment.newInstance("",""),R.id.frameLayout1)
        openFrag(ViewFragment.newInstance(1),R.id.frameLayout2)


    }

    private fun openFrag(f:Fragment,id:Int){
        supportFragmentManager.beginTransaction().replace(id,f).commit()
    }

}