package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.FragmentOderBinding
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.ui.adapters.ItemOderAdapter
import com.example.graduationthesis.utils.toCurrency

// TODO: Rename parameter arguments, choose names that match

class OderFragment : Fragment() {
    private lateinit var binding: FragmentOderBinding
    private lateinit var adapter: ItemOderAdapter
    private val listItemOder = mutableListOf<ItemCart>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOderBinding.inflate(inflater, container, false)
//        val price = arguments?.getString("price")
//        binding.edtMoney.text = price
        return binding.root
    }
    var totalPrice = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListM.layoutManager = LinearLayoutManager(context)
        binding.rvListM.setHasFixedSize(true)
        adapter = ItemOderAdapter{
            totalPrice = 0.0
            it.forEach{
                totalPrice += it.priceItemCart * it.count
            }
            binding.edtMoney.text = totalPrice.toCurrency()
        }
        binding.rvListM.adapter = adapter
        MainActivity.viewModelCart.listCartData.observe(viewLifecycleOwner, Observer {
            adapter.setItem(it)
        })

    }

}