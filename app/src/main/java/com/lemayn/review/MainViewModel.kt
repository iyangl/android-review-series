package com.lemayn.review

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * author: ly
 * date  : 2019/6/14 14:17
 * desc  :
 */
class MainViewModel : ViewModel() {

    private var liveData : MutableLiveData<List<String>>? = null

    public fun getData(): MutableLiveData<List<String>> {
        if (liveData == null) {
            liveData = MutableLiveData()
            loadData()
        }
        return liveData!!
    }

    private fun loadData() {
        val arr = ArrayList<String>()
        arr.add("1")
        arr.add("2")
        arr.add("3")
        arr.add("4")
        liveData?.postValue(arr)
    }
}