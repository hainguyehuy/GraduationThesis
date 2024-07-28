package com.example.graduationthesis.ui.views

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.Notification
import com.example.graduationthesis.data.model.SamPlePD

import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.AddProductBinding
import com.example.graduationthesis.databinding.DialogChangepasswordBinding
import com.example.graduationthesis.databinding.FragmentProductManagementBinding
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.example.graduationthesis.ui.adapters.NotificationAdapter
import com.google.firebase.database.FirebaseDatabase


class ProductManagementFragment : Fragment() {
    private lateinit var binding: FragmentProductManagementBinding
    private lateinit var dialogBinding: AddProductBinding
    private val listPD = mutableListOf<SamPlePD>()
    private lateinit var adapter: ListProductManagementAdapter
    val dataRef =
        FirebaseDatabase.getInstance().getReference("ManagementProduct").child("ChildManager")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(SamPlePD::class.java)?.let { it1 -> listPD.add(it1) }
            }
            binding.rvListM.layoutManager = LinearLayoutManager(context)
            binding.rvListM.setHasFixedSize(true)
            adapter = ListProductManagementAdapter()
            binding.rvListM.adapter = adapter
            adapter.updatePD(listPD)
        }
        binding.addProduct.setOnClickListener {
            showDialogAddPD()
        }

    }

    private fun showDialogAddPD() {
        dialogBinding = AddProductBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()
        dialogBinding.btnADD.setOnClickListener {
            saveDataPD()
            dialog.dismiss()
        }
        dialog.show()
        dialogBinding.tvAdd.setOnClickListener {
            dialog.dismiss()
        }
    }
//
//    private fun clearPD() {
//        dialogBinding.edtName.text =
//        dialogBinding.edtBrand.text.toString() =
//        dialogBinding.edtColor.text.toString()
//        dialogBinding.edtSize.text.toString()
//        dialogBinding.edtPrice.text.toString().toDouble()
//        dialogBinding.edtTitle.text.toString()
//        dialogBinding.edtURLIMG.text.toString()
//    }

    private fun saveDataPD() {
        val name = dialogBinding.edtName.text.toString()
        val brand = dialogBinding.edtBrand.text.toString()
        val color = dialogBinding.edtColor.text.toString()
        val size = dialogBinding.edtSize.text.toString()
        val price = dialogBinding.edtPrice.text.toString().toDouble()
        val title = dialogBinding.edtTitle.text.toString()
        val url = dialogBinding.edtURLIMG.text.toString()
        val id = dataRef.push().key!!
        if (name.isEmpty()) dialogBinding.edtName.error = "Điền thông tin "
        if (brand.isEmpty()) dialogBinding.edtBrand.error = "Điền thông tin "
        if (color.isEmpty()) dialogBinding.edtColor.error = "Điền thông tin "
        if (size.isEmpty()) dialogBinding.edtSize.error = "Điền thông tin "
        if (title.isEmpty()) dialogBinding.edtTitle.error = "Điền thông tin "
        if (url.isEmpty()) dialogBinding.edtURLIMG.error = "Điền thông tin "
        val product = SamPlePD(id, url, name, price, title, color, size)
        dataRef.child(id).setValue(product).addOnCompleteListener {
            Toast.makeText(context, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(context, "Lỗi ${it.message}", Toast.LENGTH_SHORT).show()

            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductManagementBinding.inflate(inflater, container, false)
        return binding.root
    }


}