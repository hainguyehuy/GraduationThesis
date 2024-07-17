package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ItemCartBinding
import com.example.graduationthesis.ui.views.ButtonClickEvent
import com.example.graduationthesis.utils.toCurrency
import java.text.NumberFormat

class ItemCartProductAdapter(private val onClickItemProduct: ((ItemCart) -> Unit)) : RecyclerView.Adapter<ItemCartProductAdapter.ItemCartViewHolder>() {
    private val listItemCart = ArrayList<ItemCart>()

    fun updateCartList(cartList: List<ItemCart>) {
        this.listItemCart.clear()
        this.listItemCart.addAll(cartList)
        notifyDataSetChanged()
    }

    var event: ButtonClickEvent? = null
    var totalPrice: Double = 0.0
    var sumTotalPrice : Double = 0.0
    var count : Int = 0
    inner class ItemCartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemCart) {
            binding.tvNameProduct.text = item.nameItemCart
            binding.tvColorProduct.text = item.colorItemCart
            binding.tvPriceProduct.text = item.priceItemCart.toString()
            binding.tvSizeProduct.text = item.sizeItemCart

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemCart.size
    }

    override fun onBindViewHolder(holder: ItemCartViewHolder, position: Int) {

        holder.bind(listItemCart[position])
        holder.binding.root.setOnClickListener {
            holder.binding.plus.setOnClickListener {
                count++
                holder.binding.tvCount.text = count.toString()
                var itemPrice = listItemCart[position].priceItemCart
                var f = NumberFormat.getInstance()
                var doublePricePD: Double = f.parse("${itemPrice}").toDouble()
                totalPrice = count * doublePricePD
                holder.binding.tvPriceProduct.text = totalPrice.toCurrency()
            }
            holder.binding.minus.setOnClickListener {
                if(count > 0){
                    count--
                    holder.binding.tvCount.text = count.toString()
                    var itemPrice = listItemCart[position].priceItemCart
                    var f = NumberFormat.getInstance()
                    var doublePricePD: Double = f.parse("${itemPrice}").toDouble()
                    totalPrice = count * doublePricePD
                    holder.binding.tvPriceProduct.text = totalPrice.toCurrency()

                }
            }
        }
    }
    fun calcTotalPrice(): Double{
        var sum : Double = 0.0
        listItemCart.forEach {
            sum += it.priceItemCart
        }
        return sum
    }
    fun updateTotalPriceProduct(){
        listItemCart.forEach {
            sumTotalPrice = calcTotalPrice()
            event?.updatePriceProduct(sumTotalPrice)
            notifyDataSetChanged()
        }
    }

}