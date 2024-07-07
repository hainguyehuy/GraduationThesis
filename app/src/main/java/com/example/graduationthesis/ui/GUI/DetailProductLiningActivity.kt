package com.example.graduationthesis.ui.GUI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityDetailProductLiningBinding
import java.lang.StringBuilder

class DetailProductLiningActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailProductLiningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductLiningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("namePD")
        val url = intent.getStringExtra("urlPD")
        val price = intent.getStringExtra("pricePD")

        binding.tvNamePD.text = StringBuilder().append("Name: $name")
        binding.tvPricePD.text = price
        if (url != null){
            val img = Uri.parse(url)
            Glide.with(this)
                .load(img)
                .into(binding.imgProductLining)
        }
    }
}