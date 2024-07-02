package com.example.graduationthesis.ui.adapters.liningAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ItemProductBinding

class ChildProductLiningAdapter(private val childProducts : List<ProductLining>): RecyclerView.Adapter<ChildProductLiningAdapter.ChildProductViewHolder>() {
    inner class ChildProductViewHolder(val binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChildProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return childProducts.size
    }

    override fun onBindViewHolder(holder: ChildProductViewHolder, position: Int) {
       holder.binding.tvNameProduct.text = childProducts[position].namePD
       holder.binding.tvPriceProduct.text = childProducts[position].pricePD
       Glide.with(holder.binding.imgProDuct.context).load(childProducts[position].urlPD).into(holder.binding.imgProDuct)
    }
}