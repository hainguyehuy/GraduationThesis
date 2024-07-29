package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ActivityOderProductBinding
import com.example.graduationthesis.ui.adapters.ItemOrderPDAdapter
import com.google.firebase.database.FirebaseDatabase

class OrderProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOderProductBinding
    private var listItemCart = mutableListOf<ItemCart>()
    private lateinit var adapterOrderPD: ItemOrderPDAdapter
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOderProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(ItemCart::class.java)?.let { it1 -> listItemCart.add(it1) }
            }
            binding.rvOderPD.layoutManager = LinearLayoutManager(this)
            binding.rvOderPD.setHasFixedSize(true)
            adapterOrderPD = ItemOrderPDAdapter()
            binding.rvOderPD.adapter = adapterOrderPD
            adapterOrderPD.setItem(listItemCart)

        }
    }
}
