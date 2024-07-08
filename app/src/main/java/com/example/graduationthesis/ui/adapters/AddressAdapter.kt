package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemAddress
import com.example.graduationthesis.databinding.FragmentAddressBinding

class AddressAdapter() : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>()  {
    private var listAddress = ArrayList<Address>()


    fun updateAddressList(addressList : List<Address>){
        this.listAddress.clear()
        this.listAddress.addAll(addressList)
        notifyDataSetChanged()
    }
    inner class AddressViewHolder(val binding : FragmentAddressBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        var binding = FragmentAddressBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddressViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return listAddress.size
    }
    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val itemAddressAdapter = ItemAddressAdapter(listAddress[position].ItemAddress)
        holder.binding.rvAddress.adapter = itemAddressAdapter
    }
}