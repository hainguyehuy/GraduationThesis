package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ActivityOderPdadminBinding
import com.example.graduationthesis.ui.adapters.ItemOrderPDAdminAdapter
import com.google.firebase.database.FirebaseDatabase

class OrderPDAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOderPdadminBinding
    private var listItemCart = mutableListOf<ItemCart>()
    private lateinit var adapterOrderPD: ItemOrderPDAdminAdapter
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOderPdadminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(ItemCart::class.java)?.let { it1 -> listItemCart.add(it1) }
            }
            binding.rvOderPD.layoutManager = LinearLayoutManager(this)
            binding.rvOderPD.setHasFixedSize(true)
            adapterOrderPD = ItemOrderPDAdminAdapter()
            binding.rvOderPD.adapter = adapterOrderPD
            adapterOrderPD.setItem(listItemCart)

        }

    }
}