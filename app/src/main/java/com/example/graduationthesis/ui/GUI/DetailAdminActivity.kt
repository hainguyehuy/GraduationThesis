package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityDetailAdminBinding
import com.example.graduationthesis.utils.toCurrency

class DetailAdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAdminBinding.inflate(layoutInflater)
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
    }
}