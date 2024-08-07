package com.example.graduationthesis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityMainBinding
import com.example.graduationthesis.ui.viewModel.CartProductViewModel
import com.example.graduationthesis.ui.views.BeginCategoryFragment
import com.example.graduationthesis.ui.views.AccountFragment
import com.example.graduationthesis.ui.views.CartFragment
import com.example.graduationthesis.ui.views.ClientsFragment
import com.example.graduationthesis.ui.views.HomeFragment
import com.example.graduationthesis.ui.views.NotifFragment
import com.example.graduationthesis.ui.views.ProductManagementFragment
import com.example.graduationthesis.ui.views.StateProductFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var viewModelCart : CartProductViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        viewModelCart = CartProductViewModel()
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

}