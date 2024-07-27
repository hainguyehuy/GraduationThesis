package com.example.graduationthesis.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.ActivityOrderBinding
import com.example.graduationthesis.databinding.FragmentUsersBinding
import com.example.graduationthesis.ui.GUI.UsersActivity
import com.example.graduationthesis.ui.adapters.OderAdapter
import com.example.graduationthesis.ui.adapters.UsersAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UsersFragment : Fragment() {

    private lateinit var binding : FragmentUsersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startActivity(Intent(context,UsersActivity::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater,container,false)
        return binding.root
    }

}