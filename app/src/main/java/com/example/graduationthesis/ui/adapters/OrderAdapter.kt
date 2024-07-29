package com.example.graduationthesis.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduationthesis.ui.views.OderFragment
import com.example.graduationthesis.ui.views.PayFragment

class OrderAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OderFragment()
            1 -> PayFragment()
            else -> PayFragment()
        }
    }
}