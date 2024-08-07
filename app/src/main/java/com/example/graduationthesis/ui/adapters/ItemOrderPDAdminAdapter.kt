package com.example.graduationthesis.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ItemOderProductBinding
import com.example.graduationthesis.databinding.ItemOrderAdminwBinding
import com.example.graduationthesis.utils.toCurrency
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.collection.LLRBNode
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
            binding.tvStatusOrderPD.text = item.statusOrderProduct
            binding.tvStatusOrderPD.setOnClickListener {
                val newBackColor = Color.GREEN
                var newStatus = "Đơn hàng giao thành công"
                updateDataInFirebase(item.idItemCart, newStatus) { isSuccess ->
                    if (isSuccess && listItem[position].statusOrderProduct == "Đơn hàng đang giao") {
                        listItem[position].statusOrderProduct = newStatus
                        binding.tvStatusOrderPD.setBackgroundColor(newBackColor)
                        notifyItemChanged(position)
                    }
//                    else if (isSuccess && listItem[position].statusOrderProduct == "Đơn hàng đang giao") {
//                        newStatus = "Đơn hàng giao thành công"
//                        listItem[position].statusOrderProduct = newStatus
//                        binding.tvStatusOrderPD.setBackgroundColor(newBackColor)
//                        notifyItemChanged(position)
//                    }
                }
            }

        }

        private fun updateDataInFirebase(
            idItemCart: String,
            newStatus: String,
            callback: (Boolean) -> Unit
        ) {
            val dataRef =
                FirebaseDatabase.getInstance().getReference("CartProduct")
            var idItemCart = dataRef.child(idItemCart)
            MaterialAlertDialogBuilder(itemView.context)
                .setTitle("Xác nhận đơn hàng")
                .setMessage("Chuyển trạng thái đơn hàng?")
                .setPositiveButton("Có") { _, _ ->

                    idItemCart.child("statusOrderProduct")
                        .setValue(newStatus)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                callback(true)
                            } else {
                                task.exception?.let {
                                    println("Failed to update name: ${it.message}")
                                }
                                callback(false)
                            }
                        }
                }
                .setNegativeButton("Không") { _, _
                    ->
                    Toast.makeText(
                        itemView.context,
                        "Chưa thay đổi trạng thái",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                .show()
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

