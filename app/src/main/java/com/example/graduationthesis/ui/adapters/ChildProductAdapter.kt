package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.Product
import com.example.graduationthesis.databinding.ItemProductBinding

class ChildProductAdapter(private val childProducts : List<Product>): RecyclerView.Adapter<ChildProductAdapter.ChildProductViewHolder>() {
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
       holder.binding.imgProDuct.setImageResource(childProducts[position].urlPD)
    }
}