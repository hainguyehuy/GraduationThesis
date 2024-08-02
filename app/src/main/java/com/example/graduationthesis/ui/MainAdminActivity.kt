package com.example.graduationthesis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityMainAdminBinding
import com.example.graduationthesis.ui.GUI.LoginActivity
import com.example.graduationthesis.ui.GUI.OrderPDAdminActivity
import com.example.graduationthesis.ui.GUI.UsersActivity
import com.example.graduationthesis.ui.views.AccountFragment
import com.example.graduationthesis.ui.views.ProductManagementFragment
import com.example.graduationthesis.ui.views.SalesStatisticsFragment
import com.example.graduationthesis.ui.views.UsersFragment

class MainAdminActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainAdminBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)
        binding = ActivityMainAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ProductManagementFragment())

        toggle = ActionBarDrawerToggle(this, binding.drawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.navi.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.app -> replaceFragment(ProductManagementFragment())
                R.id.users -> startActivity(Intent(this, UsersActivity::class.java))
                R.id.stats -> replaceFragment(SalesStatisticsFragment())
                R.id.exit -> startActivity(Intent(this,LoginActivity::class.java))
                R.id.list -> startActivity(Intent(this,OrderPDAdminActivity::class.java))
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}