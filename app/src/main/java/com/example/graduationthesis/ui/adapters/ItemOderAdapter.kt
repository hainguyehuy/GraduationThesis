package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ItemOrderBinding
import com.example.graduationthesis.utils.toCurrency

class ItemOderAdapter(var updatePrice :(ArrayList<ItemCart>) -> Unit) : RecyclerView.Adapter<ItemOderAdapter.ItemOderViewHolder>() {
    private val listItemOder = ArrayList<ItemCart>()

    fun setItem(listItemCart : List<ItemCart>){
        listItemOder.clear()
        listItemOder.addAll(listItemCart)
        notifyDataSetChanged()
    }
    inner class ItemOderViewHolder(val binding : ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        fun setData(item : ItemCart){
            binding.namePD.text = item.nameItemCart
            binding.pricePD.text = item.priceItemCart.toCurrency()
            Glide.with(binding.imgPD.context).load(item.urlItemCart)
                .into(binding.imgPD)
            updatePrice.invoke(listItemOder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemOderViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return listItemOder.size
    }

    override fun onBindViewHolder(holder: ItemOderViewHolder, position: Int) {
        holder.setData(listItemOder[position])
    }
}