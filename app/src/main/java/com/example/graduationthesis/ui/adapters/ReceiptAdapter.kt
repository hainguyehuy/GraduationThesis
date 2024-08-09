package com.example.graduationthesis.ui.adapters

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.ReceiptPD
import com.example.graduationthesis.databinding.ItemReceiptBinding
import com.example.graduationthesis.utils.toCurrency

class ReceiptAdapter(var updatePrice :(ArrayList<ReceiptPD>) -> Unit) : RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder>() {

    private val itemPDReceipt = ArrayList<ReceiptPD>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateRPD(slList: List<ReceiptPD>) {
        this.itemPDReceipt.clear()
        this.itemPDReceipt.addAll(slList)
        notifyDataSetChanged()
    }

    inner class ReceiptViewHolder(val binding: ItemReceiptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(samplePD: ReceiptPD) {
            binding.tvNameProduct.text = StringBuilder().append("Tên sản phẩm: ${samplePD.namePD}")
            binding.tvNameProduct.maxLines =2
            binding.tvNameProduct.ellipsize = TextUtils.TruncateAt.END
            binding.tvColorProduct.text = StringBuilder().append("Màu sắc: ${samplePD.colorPD}")
            binding.tvSizeProduct.text = StringBuilder().append("Kích thước: ${samplePD.sizePD}")
            binding.tvNumberPD.text = StringBuilder().append("Số lượng: ${samplePD.quantityOfGoods.toInt()}")
            binding.tvPriceProduct.text = StringBuilder().append("Giá: ${samplePD.pricePD.toCurrency()}")
            Glide.with(binding.imgItemProductCart.context).load(samplePD.urlPD)
                .into(binding.imgItemProductCart)
            binding.tvTotals.text = StringBuilder().append("Thành tiền: ${(samplePD.pricePD * samplePD.quantityOfGoods).toCurrency()}")
            updatePrice.invoke(itemPDReceipt)
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
        holder.setData(itemPDReceipt[position])
    }
}

