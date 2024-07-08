package com.example.graduationthesis.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityMainBinding
import com.example.graduationthesis.ui.adapters.BeginCategoryFragment
import com.example.graduationthesis.ui.views.AccountFragment
import com.example.graduationthesis.ui.views.AddressFragment
import com.example.graduationthesis.ui.views.CartFragment
import com.example.graduationthesis.ui.views.CategoryFragment
import com.example.graduationthesis.ui.views.HomeFragment
import com.example.graduationthesis.ui.views.NotifFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavi.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.cart -> replaceFragment(CartFragment())
                R.id.profile -> replaceFragment(AccountFragment())
                R.id.notif -> replaceFragment(NotifFragment())
                R.id.cate -> replaceFragment(BeginCategoryFragment())

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frLayout,fragment)
            commit()
        }
    }
    private fun startActivity(activity: AppCompatActivity){
        intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}