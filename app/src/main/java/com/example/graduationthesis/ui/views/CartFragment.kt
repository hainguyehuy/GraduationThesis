package com.example.graduationthesis.ui.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.databinding.FragmentCartBinding
import com.example.graduationthesis.ui.GUI.OrderActivity
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.ui.adapters.ItemCartAdapter
import com.example.graduationthesis.ui.adapters.ItemCartProductAdapter
import com.example.graduationthesis.ui.viewModel.CartProductViewModel
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.FirebaseDatabase


class CartFragment : Fragment() {
    private var listItemCart = mutableListOf<ItemCart>()
    private lateinit var binding: FragmentCartBinding
    private lateinit var adapterNew: ItemCartAdapter
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    var totalPrice = 0.0

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(ItemCart::class.java)?.let { it1 -> listItemCart.add(it1) }
            }

            binding.rvCart.layoutManager = LinearLayoutManager(context)
            binding.rvCart.setHasFixedSize(true)
            adapterNew = ItemCartAdapter {
                totalPrice = 0.0
                it.forEach {
                    totalPrice += it.priceItemCart * it.count
                }
                binding.edtMoney.text = totalPrice.toCurrency()
            }

            binding.rvCart.adapter = adapterNew
            adapterNew.setItem(listItemCart)

//        MainActivity.viewModelCart.listCartData.observe(viewLifecycleOwner, Observer {
////            binding.numberCart.text = StringBuilder().append("${it.size} SẢN PHẨM")
//            adapterNew.setItem(it)
//
//        })
            binding.btnOrder.setOnClickListener {
                startActivity(Intent(context, OrderActivity::class.java))
            }
        }

    }
}
