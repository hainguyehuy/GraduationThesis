package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.databinding.ItemHotproductBinding

class HotProductAdapter(private var hotCategory : List<HotProduct>) : RecyclerView.Adapter<HotProductAdapter.HotCateViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotCateViewHolder {
        val itemView = ItemHotproductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HotCateViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return hotCategory.size
    }

    override fun onBindViewHolder(holder: HotCateViewHolder, position: Int) {
        holder.binding.tvTitleHotProduct.text = hotCategory[position].titleHP
        Glide.with(holder.binding.imgHotProduct).load(hotCategory[position].urlImg).into(holder.binding.imgHotProduct)

    }
    inner class HotCateViewHolder(val binding : ItemHotproductBinding) : RecyclerView.ViewHolder(binding.root){


    }
}
