package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Address

class AddressAdapter() :RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {
    private var addressList = ArrayList<Address>()

    fun updateAddressList(addressList : List<Address>){
        this.addressList.clear()
        this.addressList.addAll(addressList)
        notifyDataSetChanged()
    }
    fun setFilteredList(addressList: ArrayList<Address>){
        this.addressList = addressList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_address,parent,false)
        return AddressViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val current = addressList[position]
        holder.nameAddress.text = current.nameAddress
        holder.titleAddress.text = current.titleAddress
        holder.pNumberAddress.text = current.pNumberAddress
    }
    inner class AddressViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val nameAddress : TextView = itemView.findViewById(R.id.tvNameAddress)
        val titleAddress : TextView = itemView.findViewById(R.id.tvTitleAddress)
        val pNumberAddress : TextView = itemView.findViewById(R.id.tvPhoneNumber)
    }
}