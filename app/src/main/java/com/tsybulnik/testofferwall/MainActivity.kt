package com.tsybulnik.testofferwall

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tsybulnik.testofferwall.fragments.MainFragment
import com.tsybulnik.testofferwall.fragments.ViewFragment



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        openFrag(MainFragment.newInstance(),R.id.frameLayout1)
        openFrag(ViewFragment.newInstance(),R.id.frameLayout2)
    }

    private fun openFrag(f:Fragment,id:Int){
        supportFragmentManager.beginTransaction().replace(id,f).commit()
    }

}