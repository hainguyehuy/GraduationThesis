package com.example.graduationthesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.graduationthesis.databinding.ActivityMainBinding
import com.example.graduationthesis.views.AccountFragment
import com.example.graduationthesis.views.AddressFragment
import com.example.graduationthesis.views.CartFragment
import com.example.graduationthesis.views.HomeFragment
import com.example.graduationthesis.views.NotifFragment


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
                R.id.cate -> replaceFragment(AddressFragment())
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
}