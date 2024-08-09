package com.example.graduationthesis.ui.GUI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.databinding.ActivityDetailProductLiningBinding
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.example.graduationthesis.ui.views.BottomSheetFragmentAddCart
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.FirebaseDatabase
import kotlin.text.StringBuilder

class DetailProductLiningActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailProductLiningBinding
    private val listPD = mutableListOf<SamPlePD>()
    private lateinit var adapter: ListProductManagementAdapter
    val dataRef =
        FirebaseDatabase.getInstance().getReference("ManagementProduct").child("ChildManager")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductLiningBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("namePD")
        val url = intent.getStringExtra("urlPD")
        val price = intent.getDoubleExtra("pricePD",0.0)
        val color = intent.getStringExtra("colorPD")
        val title = intent.getStringExtra("titlePD")
        val size = intent.getStringExtra("sizePD")

        binding.tvNamePD.text = StringBuilder().append("Tên sản phẩm: $name")
        binding.tvPricePD.text = price.toCurrency()
        binding.tvColor.text = StringBuilder().append("Màu sắc: $color")
        binding.tvSize.text = StringBuilder().append("Kích thước: $size")
        binding.tvTitle.text = StringBuilder().append("Mô tả sản phẩm:\n$title")
        if (url != null){
            Glide.with(applicationContext)
                .load(url)
                .into(binding.imgProductLining)
        }

        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(SamPlePD::class.java)?.let { it1 -> listPD.add(it1) }
            }
            binding.rvPDNear.layoutManager = LinearLayoutManager(this)
            binding.rvPDNear.setHasFixedSize(true)


            adapter = ListProductManagementAdapter()
            binding.rvPDNear.adapter = adapter
            adapter.updatePD(listPD)
        }
        binding.btnAddCart.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentAddCart(price!!.toDouble(),url!!)
            val bundle = Bundle()
            bundle.putString("namePD",binding.tvNamePD.text.toString())
            bundle.putString("colorPD",binding.tvColor.text.toString())
            bundle.putString("pricePD",binding.tvPricePD.text.toString())
            bundle.putString("urlPD",url)
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}