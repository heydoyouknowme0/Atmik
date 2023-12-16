package com.build.atmik.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.build.atmik.data.Entry
import com.build.atmik.databinding.CustomRowBinding

class ListAdapter(onRowClick: (Long) -> Unit) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<Entry>()
    private val onRowClick=onRowClick

    class MyViewHolder(val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        val binding = holder.binding

        binding.name.text=currentItem.entryName
        binding.Date.text=currentItem.time

        binding.rowLayout.setOnClickListener {
            onRowClick(currentItem.id)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<Entry>) {
        this.userList = user
        notifyDataSetChanged()
    }
}
