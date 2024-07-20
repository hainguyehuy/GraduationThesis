package com.example.graduationthesis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityMainAdminBinding

class MainAdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        replaceFragment(NotifFragment())
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
//                R.id.home -> replaceFragment(NotifFragment())
//                R.id.cart -> replaceFragment(CartFragment())
//                R.id.profile -> replaceFragment(AccountFragment())
//                R.id.notif -> replaceFragment(NotifFragment())
//                R.id.cate -> replaceFragment(BeginCategoryFragment())

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frLayoutAdmin,fragment)
            commit()
        }
    }

}