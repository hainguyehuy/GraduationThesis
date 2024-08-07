package com.example.graduationthesis.ui.GUI

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.Supplie
import com.example.graduationthesis.databinding.ActivitySupplierBinding
import com.example.graduationthesis.databinding.AddProductBinding
import com.example.graduationthesis.databinding.AddSupplierBinding
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.example.graduationthesis.ui.adapters.SupplierAdapter
import com.google.firebase.database.FirebaseDatabase

class SupplierActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySupplierBinding
    private lateinit var dialogBinding: AddSupplierBinding
    private lateinit var adapter: SupplierAdapter
    private val listSL = mutableListOf<Supplie>()
    val dataRef =
        FirebaseDatabase.getInstance().getReference("Supplier")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupplierBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(Supplie::class.java)?.let { it1 -> listSL.add(it1) }
            }
            binding.rvSupplier.layoutManager = LinearLayoutManager(this)
            binding.rvSupplier.setHasFixedSize(true)


            adapter = SupplierAdapter(){
                it -> onItemClick(it)
            }
            binding.rvSupplier.adapter = adapter
            adapter.updateSL(listSL)
        }
        binding.floatingActionButton2.setOnClickListener {
            showDialogAddSupplier()
        }
    }
    private fun onItemClick(supplie: Supplie) {
        var intent = Intent(this,ReceiptActivity::class.java).apply {
            putExtra("nameSL",supplie.nameSL)
        }
        startActivity(intent)
    }
    private fun showDialogAddSupplier() {
        dialogBinding = AddSupplierBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()
        dialogBinding.btnADD.setOnClickListener {
            saveDataSL()
            dialog.dismiss()
        }
        dialog.show()
        dialogBinding.tvAdd.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun saveDataSL() {
        val name = dialogBinding.edtName.text.toString()
        val address = dialogBinding.edtAddress.text.toString()
        val phone = dialogBinding.edtPhone.text.toString()
        val id = dataRef.push().key!!
        if (name.isEmpty()) dialogBinding.edtName.error = "Điền thông tin "
        if (address.isEmpty()) dialogBinding.edtAddress.error = "Điền thông tin "
        if (phone.isEmpty()) dialogBinding.edtPhone.error = "Điền thông tin "
        val product = Supplie(id, name,phone,address)
        dataRef.child(id).setValue(product).addOnCompleteListener {
            Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Lỗi ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}