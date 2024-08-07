package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.Supplie
import com.example.graduationthesis.databinding.ItemSupplierBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase

class SupplierAdapter(private val onItemClick: (Supplie) -> Unit) : RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>() {
    private val listSupplier = ArrayList<Supplie>()
    fun updateSL(slList: List<Supplie>) {
        this.listSupplier.clear()
        this.listSupplier.addAll(slList)
        notifyDataSetChanged()
    }

    inner class SupplierViewHolder(val binding: ItemSupplierBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(supplie: Supplie) {
            binding.nameSL.text = supplie.nameSL
            binding.addressSL.text = supplie.addressSL
            binding.phoneSL.text = supplie.phoneSL

            itemView.setOnClickListener {
                onItemClick(supplie)
            }
            binding.deletePD.setOnClickListener {
                MaterialAlertDialogBuilder(itemView.context)
                    .setTitle("Xoá nhà cung cấp")
                    .setMessage("Bạn có muốn xoá nhà cung cấp này không?")
                    .setPositiveButton("Có") { _, _ ->
                        val dataRef =
                            FirebaseDatabase.getInstance().getReference("Supplier")
                        dataRef.child(listSupplier[layoutPosition].idSL).removeValue()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        val binding =
            ItemSupplierBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SupplierViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSupplier.size
    }

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        holder.setData(listSupplier.get(position))
    }
}