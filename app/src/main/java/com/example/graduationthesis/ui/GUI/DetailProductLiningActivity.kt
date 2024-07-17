package com.example.graduationthesis.ui.GUI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityDetailProductLiningBinding
import com.example.graduationthesis.ui.views.BottomSheetFragmentAddCart
import kotlin.text.StringBuilder

class DetailProductLiningActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailProductLiningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductLiningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("namePD")
        val url = intent.getStringExtra("urlPD")
        val price = intent.getStringExtra("pricePD")
        val color = intent.getStringExtra("colorPD")
        val title = intent.getStringExtra("titlePD")
        val size = intent.getStringExtra("sizePD")

        binding.tvNamePD.text = name
        binding.tvPricePD.text = price
        binding.tvColor.text = StringBuilder().append("Màu sắc: $color")
        binding.tvSize.text = StringBuilder().append("Kích thước: $size")
        binding.tvTitle.text = StringBuilder().append("Mô tả: $title")
        if (url != null){
            val img = Uri.parse(url)
            Glide.with(this)
                .load(img)
                .into(binding.imgProductLining)
        }
        binding.btnAddCart.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragmentAddCart(price!!.toDouble())
            val bundle = Bundle()
            bundle.putString("namePD",binding.tvNamePD.text.toString())
            bundle.putString("colorPD",binding.tvColor.text.toString())
            bundle.putString("pricePD",binding.tvPricePD.text.toString())
            bundle.putString("urlPD",binding.imgProductLining.toString())
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}