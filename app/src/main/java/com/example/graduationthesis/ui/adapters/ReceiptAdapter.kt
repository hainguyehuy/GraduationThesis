package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.Supplie
import com.example.graduationthesis.databinding.ItemReceiptBinding
import com.example.graduationthesis.databinding.ItemSupplierBinding
import com.example.graduationthesis.utils.toCurrency
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase

class ReceiptAdapter() : RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    private val itemPDReceipt = ArrayList<SamPlePD>()
    fun updateRPD(slList: List<SamPlePD>) {
        this.itemPDReceipt.clear()
        this.itemPDReceipt.addAll(slList)
        notifyDataSetChanged()
    }

    inner class ReceiptViewHolder(val binding: ItemReceiptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(samplePD: SamPlePD) {
            binding.tvNameProduct.text = samplePD.namePD
            binding.tvColorProduct.text = samplePD.colorPD
            binding.tvPriceProduct.text = samplePD.pricePD.toCurrency()
            binding.tvSizeProduct.text = samplePD.sizePD
            binding.tvNumberPD.text = samplePD.quantityOfGoods.toCurrency()
            Glide.with(binding.imgItemProductCart.context).load(samplePD.urlPD)
                .into(binding.imgItemProductCart)
            binding.tvTotals.text = (samplePD.pricePD * samplePD.quantityOfGoods).toCurrency()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val binding =
            ItemReceiptBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReceiptViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemPDReceipt.size
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        holder.setData(itemPDReceipt.get(position))
    }
}

