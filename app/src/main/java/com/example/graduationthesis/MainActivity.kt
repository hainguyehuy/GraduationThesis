package com.example.graduationthesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.graduationthesis.GUI.LoginActivity
import com.example.graduationthesis.GUI.SignUpActivity
import com.example.graduationthesis.models.ImgItem
import com.example.graduationthesis.adapters.ImageAdapter
import com.example.graduationthesis.databinding.ActivityMainBinding
import com.example.graduationthesis.views.AccountFragment
import com.example.graduationthesis.views.CartFragment
import com.example.graduationthesis.views.HomeFragment
import com.example.graduationthesis.views.NotifFragment
import java.util.UUID


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
                R.id.cart -> replaceFragment(CartFragment())
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