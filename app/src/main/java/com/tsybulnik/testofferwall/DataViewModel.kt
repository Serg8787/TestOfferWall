package com.tsybulnik.testofferwall

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel() : ViewModel() {

    val str : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val typeElement: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}