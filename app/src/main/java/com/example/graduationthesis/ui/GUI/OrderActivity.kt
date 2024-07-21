package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityOrderBinding
import com.example.graduationthesis.ui.adapters.OderAdapter
import com.example.graduationthesis.ui.views.OderFragment
import com.example.graduationthesis.ui.views.PayFragment
import com.google.android.material.tabs.TabLayoutMediator

class OrderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager2.adapter = OderAdapter(this)
        TabLayoutMediator(binding.tablayout,binding.viewpager2){
            tab, position ->
            when(position){
                0 -> tab.text = "Đơn hàng"
                1 -> tab.text = "Thông tin thanh toán"

            }
        }.attach()
    }
}