package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ItemCartBinding
import com.example.graduationthesis.databinding.ItemStateProductBinding
import com.example.graduationthesis.utils.toCurrency
import java.text.NumberFormat


    class StateProductAdapter() : RecyclerView.Adapter<StateProductAdapter.ViewHolder>(){

        private val listItem = ArrayList<ItemCart>()
        inner class ViewHolder(val binding : ItemStateProductBinding) : RecyclerView.ViewHolder(binding.root){
            var totalPrice: Double = 0.0

            fun setData(item: ItemCart){
                binding.tvNameProduct.text = item.nameItemCart
                binding.tvColorProduct.text = item.colorItemCart
                binding.tvPriceProduct.text = item.priceItemCart.toCurrency()
                binding.tvSizeProduct.text = item.sizeItemCart
                binding.tvCountProduct.text = StringBuilder().append("Số lượng: ${item.count}")
                Glide.with(binding.imgItemProductCart.context).load(item.urlItemCart)
                    .into(binding.imgItemProductCart)

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemStateProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun getItemCount(): Int {
            return listItem.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setData(listItem.get(position))
        }
        val listData: ArrayList<ItemCart> get() = listItem

        fun setItem(listItemCart : List<ItemCart>){
            listItem.clear()
            listItem.addAll(listItemCart)
            notifyDataSetChanged()
        }

    }
