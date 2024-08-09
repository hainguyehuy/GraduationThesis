package com.example.graduationthesis.ui.GUI

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ActivityDetailProductLiningBinding
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.example.graduationthesis.ui.adapters.liningAdapter.CategorysLiningAdapter
import com.example.graduationthesis.ui.views.BottomSheetFragmentAddCart
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlin.text.StringBuilder

class DetailProductLiningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductLiningBinding
    private val listPD = mutableListOf<SamPlePD>()
    private lateinit var adapter: ListProductManagementAdapter
    private var listProduct = mutableListOf<CategorysLining>()
    private lateinit var database: DatabaseReference

    private lateinit var adapterProductLining: CategorysLiningAdapter

    val dataRef =
        FirebaseDatabase.getInstance().getReference("ManagementProduct").child("ChildManager")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductLiningBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("namePD")
        val url = intent.getStringExtra("urlPD")
        val price = intent.getDoubleExtra("pricePD", 0.0)
        val color = intent.getStringExtra("colorPD")
        val title = intent.getStringExtra("titlePD")
        val size = intent.getStringExtra("sizePD")

        binding.tvNamePD.text = StringBuilder().append("Tên sản phẩm: $name")
        binding.tvPricePD.text = StringBuilder().append("Giá sản phẩm: ${price.toCurrency()}")
        binding.tvColor.text = StringBuilder().append("Màu sắc: $color")
        binding.tvSize.text = StringBuilder().append("Kích thước: $size")
        binding.tvTitle.text = StringBuilder().append("Mô tả sản phẩm:\n$title")
        if (url != null) {
            Glide.with(applicationContext)
                .load(url)
                .into(binding.imgProductLining)
        }

        FirebaseDatabase.getInstance().getReference("SampleCate")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    var gson = Gson()
                    val json = Gson().toJson(it.value)
                    val data = gson.fromJson(json, CategorysLining::class.java)
                    listProduct.add(data)
                }
                binding.rvPDNear.layoutManager = LinearLayoutManager(this)
                binding.rvPDNear.setHasFixedSize(true)
                adapterProductLining = CategorysLiningAdapter(::onItemClick)
                binding.rvPDNear.adapter = adapterProductLining
                adapterProductLining.updateCategorys(listProduct)
            }
        binding.btnAddCart.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentAddCart(price!!.toDouble(), url!!)
            val bundle = Bundle()
            bundle.putString("namePD", binding.tvNamePD.text.toString())
            bundle.putString("colorPD", binding.tvColor.text.toString())
            bundle.putString("pricePD", binding.tvPricePD.text.toString())
            bundle.putString("urlPD", url)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        binding.btnBuyNow.setOnClickListener {
            saveItemCart()
        }
    }
    private fun saveItemCart() {

        val nameItemCart = binding.tvNamePD.text.toString()
        val colorItemCart = binding.tvColor.text.toString()
        val priceItemCart = binding.tvPricePD.text.toString()
        val sizeItemCart = binding.tvSize.text.toString()
        val urlItemCart = binding.imgProductLining.toString()
        val statusOrderProduct = "Chờ xác nhận"

        try {
            database = FirebaseDatabase.getInstance().getReference("CartProduct").push()
            val itemCart = ItemCart(
                database.key.toString(),
                nameItemCart,
                colorItemCart,
                priceItemCart.toDouble(),
                sizeItemCart,
                urlItemCart,
                1,
                statusOrderProduct
            )
            database.setValue(itemCart)
                .addOnSuccessListener {
                    Toast.makeText(this, "Thêm giỏ hàng thành công!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "save data fail", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Log.e("data", e.message.toString())
        }
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
}