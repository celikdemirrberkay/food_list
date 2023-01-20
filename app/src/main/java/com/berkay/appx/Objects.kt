package com.berkay.appx

import android.annotation.SuppressLint
import com.berkay.appx.databinding.ActivityMainBinding

object Objects {

    @SuppressLint("StaticFieldLeak")
    lateinit var binding : ActivityMainBinding
    lateinit var adapter : MyAdapter
    val myFoodList = ArrayList<String>()
    val dateList = ArrayList<String>()

}