package com.example.demo_app.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo_app.data.model.RowData
import com.example.demo_app.databinding.ItemRowBinding

class DemoListAdapter(private val context: Context, private val items:List<RowData>) :
RecyclerView.Adapter<DemoListAdapter.DemoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DemoViewHolder(
        ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val row = items[position]
        holder.binding.apply {
            tvRowTitle.text = row.title
            tvRowDesc.text = row.description
            Glide.with(context).load(row.imageHref).into(ivRowImage)
        }
    }

    override fun getItemCount() = items.size

    inner class DemoViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
}