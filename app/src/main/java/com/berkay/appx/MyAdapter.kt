package com.berkay.appx

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkay.appx.databinding.RecyclerRowBinding

class MyAdapter(val myList : ArrayList<String> , val dateList: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.NHolder>(){
    class NHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return NHolder(binding)

    }

    override fun onBindViewHolder(holder: NHolder, position: Int) {

        holder.setIsRecyclable(false)
        holder.binding.dateText.text = dateList.get(position % 4)

        holder.binding.soupText.text = myList.get(position*4)
        holder.binding.foodsText.text = myList.get(position*4+1)
        holder.binding.pastaText.text = myList.get(position*4+2)
        holder.binding.dessertText.text = myList.get(position*4+3)



    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}