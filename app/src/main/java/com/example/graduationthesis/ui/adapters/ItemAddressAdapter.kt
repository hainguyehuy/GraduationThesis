package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ItemAddress
import com.example.graduationthesis.databinding.ItemAddressBinding

class ItemAddressAdapter(private var addressList : List<ItemAddress>) :RecyclerView.Adapter<ItemAddressAdapter.AddressViewHolder>() {

    fun setFilteredList(addressList: ArrayList<ItemAddress>){
        this.addressList = addressList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddressViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return addressList.size
    }
    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val current = addressList[position]
        holder.binding.tvNameAddress.text = current.nameAddress
        holder.binding.tvTitleAddress.text = current.titleAddress
        holder.binding.tvPhoneNumber.text = current.pNumberAddress
    }
    inner class AddressViewHolder(val binding : ItemAddressBinding) : RecyclerView.ViewHolder(binding.root){

    }
}