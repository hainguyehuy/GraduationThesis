package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ItemOderProductBinding
import com.example.graduationthesis.databinding.ItemOrderAdminwBinding
import com.example.graduationthesis.utils.toCurrency
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ItemOrderPDAdminAdapter() : RecyclerView.Adapter<ItemOrderPDAdminAdapter.ViewHolder>() {

    private val listItem = ArrayList<ItemCart>()
    fun setItem(listItemOderPD: List<ItemCart>) {
        listItem.clear()
        listItem.addAll(listItemOderPD)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemOrderAdminwBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = currentDate.format(formatter)
        fun setData(item: ItemCart) {
            binding.namePD.text = item.nameItemCart
            binding.pricePD.text = item.priceItemCart.toCurrency()
            binding.tvDateOder.text = formattedDate
            Glide.with(binding.imgPD.context).load(item.urlItemCart)
                .into(binding.imgPD)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOrderAdminwBinding.inflate(
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

