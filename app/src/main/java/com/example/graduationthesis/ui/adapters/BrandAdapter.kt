package com.example.graduationthesis.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.lining.BrandPD
import com.example.graduationthesis.databinding.BrandBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase

class BrandAdapter(private val onItemClick: (BrandPD) -> Unit) :  RecyclerView.Adapter<BrandAdapter.BrandViewHolder>(){

    private val listSupplier = ArrayList<BrandPD>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateSL(slList: List<BrandPD>) {
        this.listSupplier.clear()
        this.listSupplier.addAll(slList)
        notifyDataSetChanged()
    }

    inner class BrandViewHolder(val binding: BrandBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(brand: BrandPD) {
            binding.nameBrand.text = brand.nameBrand
            Glide.with(binding.imgBrand.context).load(brand.logoBrand)
                .into(binding.imgBrand)

            itemView.setOnClickListener {
                onItemClick(brand)
            }
            binding.deletePD.setOnClickListener {
                MaterialAlertDialogBuilder(itemView.context)
                    .setTitle("Xoá nhà thương hiệu")
                    .setMessage("Bạn có muốn xoá thương hiệu này không?")
                    .setPositiveButton("Có") { _, _ ->
                        val dataRef =
                            FirebaseDatabase.getInstance().getReference("Brand")
                        dataRef.child(listSupplier[layoutPosition].idBrand).removeValue()
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
//            binding.editPD.setOnClickListener {
//                val dialogBinding = UpdateSupplierBinding()
//                dialogBinding = UpdateSupplierBinding.inflate(LayoutInflater.from(this))
//                val dialog = AlertDialog.Builder(Context)
//                    .setView(dialogBinding.root)
//                    .create()
//                dialogBinding.btnADD.setOnClickListener {
//                    saveDataSL()
//                    dialog.dismiss()
//                }
//                dialog.show()
//                dialogBinding.tvAdd.setOnClickListener {
//                    dialog.dismiss()
//                }
//                val dataRef =
//                    FirebaseDatabase.getInstance().getReference("Supplier")
//                dataRef.child(listSupplier[layoutPosition].idSL).removeValue()
//                    .addOnSuccessListener {
//                        Toast.makeText(
//                            itemView.context,
//                            "Xoá Thành Công!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    .addOnFailureListener {
//                        Toast.makeText(
//                            itemView.context,
//                            "Xoá Không Thành Công!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding =
            BrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSupplier.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.setData(listSupplier[position])
    }
}