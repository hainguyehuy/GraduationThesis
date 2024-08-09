package com.example.graduationthesis.ui.GUI

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.Supplie
import com.example.graduationthesis.data.model.lining.BrandPD
import com.example.graduationthesis.databinding.ActivityBrandBinding
import com.example.graduationthesis.databinding.AddBrandBinding
import com.example.graduationthesis.databinding.AddSupplierBinding
import com.example.graduationthesis.ui.adapters.BrandAdapter
import com.example.graduationthesis.ui.adapters.SupplierAdapter
import com.google.firebase.database.FirebaseDatabase

class BrandActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBrandBinding
    private lateinit var dialogBinding: AddBrandBinding
    private lateinit var adapter: BrandAdapter
    private val listSL = mutableListOf<BrandPD>()
    val dataRef =
        FirebaseDatabase.getInstance().getReference("Brand")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(BrandPD::class.java)?.let { it1 -> listSL.add(it1) }
            }
            binding.rvSupplier.layoutManager = LinearLayoutManager(this)
            binding.rvSupplier.setHasFixedSize(true)


            adapter = BrandAdapter(){
                    it -> onItemClick(it)
            }
            binding.rvSupplier.adapter = adapter
            adapter.updateSL(listSL)
        }
        binding.floatingActionButton2.setOnClickListener {
            showDialogAddSupplier()
        }
    }
    private fun onItemClick(brand: BrandPD) {
//        var intent = Intent(this,ReceiptActivity::class.java).apply {
//            putExtra("nameSL",brand.nameBrand)
//        }
        startActivity(Intent(this,ProductManagementActivity::class.java))
    }
    private fun showDialogAddSupplier() {
        dialogBinding = AddBrandBinding.inflate(LayoutInflater.from(this))
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
        val url = dialogBinding.edtUrl.text.toString()
        val id = dataRef.push().key!!
        if (name.isEmpty()) dialogBinding.edtName.error = "Điền thông tin "
        if (url.isEmpty()) dialogBinding.edtUrl.error = "Điền thông tin "
        val product = Supplie(id, name,url)
        dataRef.child(id).setValue(product).addOnCompleteListener {
            Toast.makeText(this, "Thêm thương hiệu thành công", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Lỗi ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}