package com.berkay.appx

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.loader.content.AsyncTaskLoader
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkay.appx.Objects.adapter
import com.berkay.appx.Objects.binding
import com.berkay.appx.Objects.dateList
import com.berkay.appx.Objects.myFoodList
import com.berkay.appx.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {

        @SuppressLint("NotifyDataSetChanged")
        override fun onCreate(savedInstanceState: Bundle?) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            FetchDataFromInternet(myFoodList,dateList).execute()
            binding.swipeRefreshLayout.setOnRefreshListener {

                binding.recyclerView.visibility = View.INVISIBLE
                binding.progressBar2.visibility = View.VISIBLE
                binding.swipeRefreshLayout.isRefreshing = false
                FetchDataFromInternet(myFoodList, dateList).execute()

            }


        }


    private class FetchDataFromInternet (
         val myFoodList: ArrayList<String>,
         val dateList:ArrayList<String>,
         ): AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {

            myFoodList.clear()
            dateList.clear()

            try{
                val docs = Jsoup.connect("https://sks.marmara.edu.tr/yemek").get()
                val fetchedFoodData = docs
                    .select("ul.list-border.list-dashed")
                    .select("li")
                val fetchedDateData = docs
                    .select("th.text-center")

                for (i in 0..19){
                    myFoodList.add(fetchedFoodData[i].text())
                }

                for (i in 0..4){
                    dateList.add(fetchedDateData[i].text())
                }

            }catch (e: IOException){
                e.printStackTrace()
            }
            return  null
        }

        override fun onPreExecute() {
            super.onPreExecute()
            binding.progressBar2.visibility = View.VISIBLE
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)

            adapter = MyAdapter(myFoodList,dateList)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.visibility = View.VISIBLE
            binding.progressBar2.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false

        }
    }
}