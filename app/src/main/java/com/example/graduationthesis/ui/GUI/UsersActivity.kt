package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationthesis.databinding.ActivityUsersBinding
import com.example.graduationthesis.ui.adapters.UsersAdapter
import com.google.android.material.tabs.TabLayoutMediator

class UsersActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager2.adapter = UsersAdapter(this)
        TabLayoutMediator(binding.tablayout,binding.viewpager2){
                tab, position ->
            when(position){
                0 -> tab.text = "Nhân Viên"
                1 -> tab.text = "Khách Hàng"

            }
        }.attach()
    }
}