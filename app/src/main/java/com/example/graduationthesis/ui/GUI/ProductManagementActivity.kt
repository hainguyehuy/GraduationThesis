package com.example.graduationthesis.ui.GUI

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.databinding.ActivityProductManagementBinding
import com.example.graduationthesis.databinding.AddProductBinding
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

class ProductManagementActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductManagementBinding
    private lateinit var dialogBinding: AddProductBinding
    private val listPD = mutableListOf<SamPlePD>()
    private val newListPD = mutableListOf<SamPlePD>()
    private lateinit var adapter: ListProductManagementAdapter
    val dataRef =
        FirebaseDatabase.getInstance().getReference("ManagementProduct").child("ChildManager")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(SamPlePD::class.java)?.let { it1 -> listPD.add(it1) }
            }
            binding.rvListM.layoutManager = LinearLayoutManager(this)
            binding.rvListM.setHasFixedSize(true)


            adapter = ListProductManagementAdapter()
            binding.rvListM.adapter = adapter
            adapter.updatePD(listPD)
        }
        binding.addProduct.setOnClickListener {
            showDialogAddPD()
        }
        binding.searchPD.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    adapter.updatePD(listPD.filter {
                            it -> it.namePD.lowercase().contains(searchText)
                    })
                }
                else
                {
                    listPD.clear()
                    listPD.addAll(newListPD)
                    adapter.updatePD(listPD)
                }
                return false
            }

        })
    }
    private fun showDialogAddPD() {
        dialogBinding = AddProductBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
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
            Toast.makeText(this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Lỗi ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}