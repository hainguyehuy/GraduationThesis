package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.ItemOderProduct
import com.example.graduationthesis.databinding.ItemOderProductBinding
import com.example.graduationthesis.utils.toCurrency


class ItemOderPDAdapter() : RecyclerView.Adapter<ItemOderPDAdapter.ViewHolder>() {

    private val listItem = ArrayList<ItemCart>()
    fun setItem(listItemOderPD : List<ItemCart>){
        listItem.clear()
        listItem.addAll(listItemOderPD)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemOderProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: ItemCart) {
            binding.namePD.text = item.nameItemCart
            binding.pricePD.text = item.priceItemCart.toCurrency()
            binding.tvDateOder.text = "2024/30/7"
            Glide.with(binding.imgPD.context).load(item.urlItemCart)
                .into(binding.imgPD)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOderProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return listItem.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(listItem.get(position))
    }
}
