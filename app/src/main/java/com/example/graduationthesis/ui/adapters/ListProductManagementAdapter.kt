package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ListProductBinding
import com.example.graduationthesis.utils.toCurrency
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase

class ListProductManagementAdapter() :
    RecyclerView.Adapter<ListProductManagementAdapter.ListProductManagementViewHolder>() {
    private val listPD = ArrayList<SamPlePD>()
    fun updatePD(pdList: List<SamPlePD>) {
        this.listPD.clear()
        this.listPD.addAll(pdList)
        notifyDataSetChanged()
    }

    inner class ListProductManagementViewHolder(val binding: ListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: SamPlePD) {
            binding.namePD.text = item.namePD
            binding.pricePD.text = item.pricePD.toCurrency()
            Glide.with(binding.imgPD.context).load(item.urlPD)
                .into(binding.imgPD)
            binding.deletePD.setOnClickListener {
                MaterialAlertDialogBuilder(itemView.context)
                    .setTitle("Xoá sản phẩm")
                    .setMessage("Bạn có muốn xoá sản phẩm này không?")
                    .setPositiveButton("Có") { _, _ ->
                        val dataRef =
                            FirebaseDatabase.getInstance().getReference("ManagementProduct")
                                .child("ChildManager")
                        dataRef.child(listPD[layoutPosition].idPD).removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(
                                    itemView.context,
                                    "Xoá Thành Công!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    itemView.context,
                                    "Xoá Không Thành Công!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                    .setNegativeButton("Không") { _, _
                        ->
                        Toast.makeText(
                            itemView.context,
                            "Quay Lại",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    .show()
            }
        }
    }

    private fun removeItem(layoutPosition: Int) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListProductManagementViewHolder {
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
