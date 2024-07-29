package com.example.graduationthesis.ui.views

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.FragmentOderBinding
import com.example.graduationthesis.ui.GUI.OderProductActivity
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.ui.adapters.ItemOderAdapter
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match

class OderFragment : Fragment() {
    private lateinit var binding: FragmentOderBinding
    private lateinit var adapter: ItemOderAdapter
    private val listItemOder = mutableListOf<ItemCart>()
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")
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
        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(ItemCart::class.java)?.let { it1 -> listItemOder.add(it1) }
            }
            binding.rvListM.layoutManager = LinearLayoutManager(context)
            binding.rvListM.setHasFixedSize(true)
            adapter = ItemOderAdapter {
                totalPrice = 0.0
                it.forEach {
                    totalPrice += it.priceItemCart * it.count
                }
                binding.edtMoney.text = totalPrice.toCurrency()
            }
            binding.rvListM.adapter = adapter
            adapter.setItem(listItemOder)

            binding.btnPay.setOnClickListener {
                getCurrentDateTime()
                startActivity(Intent(context, OderProductActivity::class.java))
            }

        }
    }
    private fun getCurrentDateTime() {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDateTime)
        binding.tvDate.text = formattedDate
    }
}