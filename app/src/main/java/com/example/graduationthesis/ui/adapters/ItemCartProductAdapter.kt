package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ItemCartBinding

class ItemCartProductAdapter() : RecyclerView.Adapter<ItemCartProductAdapter.ItemCartViewHolder>()  {
    private val listItemCart = ArrayList<ItemCart>()

    fun updateCartList(cartList : List<ItemCart>){
        this.listItemCart.clear()
        this.listItemCart.addAll(cartList)
        notifyDataSetChanged()
    }
    inner class ItemCartViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val nameProduct = itemView.findViewById<TextView>(R.id.tvNameProduct)!!
        val colorProduct = itemView.findViewById<TextView>(R.id.tvColorProduct)!!
        val priceProduct = itemView.findViewById<TextView>(R.id.tvPriceProduct)!!
        val sizeProduct = itemView.findViewById<TextView>(R.id.tvSizeProduct)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        return ItemCartViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listItemCart.size
    }

    override fun onBindViewHolder(holder: ItemCartViewHolder, position: Int) {
        holder.nameProduct.text = listItemCart[position].nameItemCart
        holder.colorProduct.text = listItemCart[position].colorItemCart
        holder.priceProduct.text = listItemCart[position].priceItemCart.toString()
        holder.sizeProduct.text = listItemCart[position].sizeItemCart
    }
}