package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ListProductBinding
import com.example.graduationthesis.utils.toCurrency

class ListProductManagementAdapter(private val listPD: List<SamPlePD>) :
    RecyclerView.Adapter<ListProductManagementAdapter.ListProductManagementViewHolder>() {


    inner class ListProductManagementViewHolder(val binding: ListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: SamPlePD) {
            binding.namePD.text = item.namePD
            binding.pricePD.text = item.pricePD.toCurrency()
            Glide.with(binding.imgPD.context).load(item.urlPD)
                .into(binding.imgPD)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductManagementViewHolder {
        val binding = ListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListProductManagementViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPD.size
    }

    override fun onBindViewHolder(holder: ListProductManagementViewHolder, position: Int) {
        holder.setData(listPD.get(position))

    }
}
