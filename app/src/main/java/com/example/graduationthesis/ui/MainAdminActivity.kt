package com.example.graduationthesis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityMainAdminBinding
import com.example.graduationthesis.ui.views.AccountFragment
import com.example.graduationthesis.ui.views.ProductManagementFragment
import com.example.graduationthesis.ui.views.SalesStatisticsFragment
import com.example.graduationthesis.ui.views.UsersFragment

class MainAdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ProductManagementFragment())
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.app -> replaceFragment(ProductManagementFragment())
                R.id.users -> replaceFragment(UsersFragment())
                R.id.stats -> replaceFragment(SalesStatisticsFragment())
                R.id.exit -> replaceFragment(AccountFragment())
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