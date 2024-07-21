package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.FragmentStateProductBinding
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.ui.adapters.ItemCartAdapter
import com.example.graduationthesis.ui.adapters.StateProductAdapter
import com.example.graduationthesis.utils.toCurrency

class StateProductFragment : Fragment() {
    private lateinit var binding : FragmentStateProductBinding
    private lateinit var adapterNew: StateProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvStatePD.layoutManager = LinearLayoutManager(context)
        binding.rvStatePD.setHasFixedSize(true)
        adapterNew = StateProductAdapter()

        binding.rvStatePD.adapter = adapterNew

        MainActivity.viewModelCart.listCartData.observe(viewLifecycleOwner, Observer {
            adapterNew.setItem(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStateProductBinding.inflate(inflater,container,false)
        return binding.root
    }


}