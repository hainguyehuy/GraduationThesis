package com.example.graduationthesis.ui.adapters.liningAdapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
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
                val spannableString = SpannableString("${item.priceSale.toCurrency()}")
                spannableString.setSpan(StrikethroughSpan(),0,spannableString.length-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.tvNameProduct.text = item.namePD
                binding.tvPriceProduct.text = item.pricePD.toCurrency()
                binding.tvPricePDSale.text = spannableString
                binding.numberSale.text = item.numberSale
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

    }
}