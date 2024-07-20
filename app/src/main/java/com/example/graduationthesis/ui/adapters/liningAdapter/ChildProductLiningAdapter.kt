package com.example.graduationthesis.ui.adapters.liningAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ItemProductBinding
import com.example.graduationthesis.utils.toCurrency

class ChildProductLiningAdapter(
    private val childProducts: List<ProductLining>,
    private val onItemClick: ((ProductLining) -> Unit)
) : RecyclerView.Adapter<ChildProductLiningAdapter.ChildProductViewHolder>() {
    inner class ChildProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun setData(item: ProductLining){
                binding.tvNameProduct.text = item.namePD
                binding.tvPriceProduct.text = item.pricePD
                Glide.with(binding.imgProDuct.context).load(item.urlPD)
                    .into(binding.imgProDuct)
                binding.root.setOnClickListener {
                    onItemClick(item)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return childProducts.size
    }

    override fun onBindViewHolder(holder: ChildProductViewHolder, position: Int) {
        holder.setData(childProducts.get(position))
//        holder.binding.tvNameProduct.text = childProducts[position].namePD
//        holder.binding.tvPriceProduct.text = childProducts[position].pricePD
//        Glide.with(holder.binding.imgProDuct.context).load(childProducts[position].urlPD)
//            .into(holder.binding.imgProDuct)
//        holder.binding.root.setOnClickListener {
//            onItemClick(childProducts[position])
//        }
    }
}