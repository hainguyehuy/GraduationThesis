package com.example.graduationthesis.ui.views

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ActivityMainAdactivityBinding
import com.example.graduationthesis.ui.GUI.DetailProductLiningActivity
import com.example.graduationthesis.ui.adapters.ItemOrderPDAdminAdapter
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.example.graduationthesis.ui.adapters.liningAdapter.CategorysLiningAdapter
import com.example.graduationthesis.ui.viewModel.ProductLiningViewModel
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import java.util.Locale

class MainADActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainAdactivityBinding
    //rvPD
    private lateinit var adapterProductLining: CategorysLiningAdapter
    private lateinit var viewModelPDLining: ProductLiningViewModel
    private val listPD = mutableListOf<CategorysLining>()

    //rvOrderPd
    private var listItemCart = mutableListOf<ItemCart>()
    private lateinit var adapterOrderPD: ItemOrderPDAdminAdapter
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAdactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val dataRef =
//            FirebaseDatabase.getInstance().getReference("SampleCate").child("Category").child("Products")
//        dataRef.get().addOnSuccessListener {
//            for (Doc in it.children) {
//                Doc.getValue(CategorysLining::class.java)?.let { it1 -> listPD.add(it1) }
//            }
//            binding.rvNewPD.layoutManager = LinearLayoutManager(this)
//            binding.rvNewPD.setHasFixedSize(true)
//            adapterProductLining = CategorysLiningAdapter(::onItemClick)
//            binding.rvNewPD.adapter = adapterProductLining
//            adapterProductLining.updateCategorys(listPD)
//        }
        FirebaseDatabase.getInstance().getReference("SampleCate")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    var gson = Gson()
                    val json = Gson().toJson(it.value)
                    val data = gson.fromJson(json, CategorysLining::class.java)
                    listPD.add(data)
                }
                binding.rvNewPD.layoutManager = LinearLayoutManager(this)
                binding.rvNewPD.setHasFixedSize(true)
                adapterProductLining = CategorysLiningAdapter(::onItemClick)
                binding.rvNewPD.adapter = adapterProductLining
                adapterProductLining.updateCategorys(listPD)
            }

        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(ItemCart::class.java)?.let { it1 -> listItemCart.add(it1) }
            }
            binding.rvOrderPD.layoutManager = LinearLayoutManager(this)
            binding.rvOrderPD.setHasFixedSize(true)
            adapterOrderPD = ItemOrderPDAdminAdapter()
            binding.rvOrderPD.adapter = adapterOrderPD
            adapterOrderPD.setItem(listItemCart)
        }

        fetchTotalAmountFromFirebase()

    }
    private fun onItemClick(itemResponse: ProductLining) {
        var intent = Intent(this, DetailProductLiningActivity::class.java).apply {
            putExtra("namePD", itemResponse.namePD)
            putExtra("urlPD", itemResponse.urlPD)
            putExtra("pricePD", itemResponse.pricePD)
            putExtra("colorPD", itemResponse.colorPD)
            putExtra("titlePD", itemResponse.titlePD)
            putExtra("sizePD", itemResponse.sizePD)
        }
        startActivity(intent)

    }

    private fun fetchTotalAmountFromFirebase() {
        val query = dataRef.orderByChild("statusOrderProduct").equalTo("Đơn hàng giao thành công")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var totalAmount = 0.0
                var count = 0
                for (snapshot in dataSnapshot.children) {
                    count++
                    val amount = snapshot.child("priceItemCart").getValue(Double::class.java)
                    val item = snapshot.getValue(ItemCart::class.java)
                    item?.let {
                        if(amount != null){
                            totalAmount += amount
                        }
                    }
                }
                binding.countOrder.text = StringBuilder().append("Số đơn đã bán\n$count")
                binding.doanhthu.text = StringBuilder().append("Doanh thu\n${totalAmount.toCurrency()}")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Database error: ${databaseError.message}")
            }
        })

    }


}