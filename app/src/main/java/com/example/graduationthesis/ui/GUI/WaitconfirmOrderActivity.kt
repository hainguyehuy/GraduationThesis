package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.ActivityWaitconfirmOrderBinding
import com.example.graduationthesis.ui.adapters.ItemOrderPDAdminAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class WaitconfirmOrderActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWaitconfirmOrderBinding
    private var listItemCart = mutableListOf<ItemCart>()
    private lateinit var adapterOrderPD: ItemOrderPDAdminAdapter
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaitconfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvWaitConfirmOderPD.layoutManager = LinearLayoutManager(this)
        binding.rvWaitConfirmOderPD.setHasFixedSize(true)
        adapterOrderPD = ItemOrderPDAdminAdapter()
        binding.rvWaitConfirmOderPD.adapter = adapterOrderPD
        fetchUsersByField("statusOrderProduct","Chờ xác nhận"){

            adapterOrderPD.setItem(listItemCart)
        }
    }

    private fun fetchUsersByField(fieldName: String, fieldValue: String, callback: (List<ItemCart>) -> Unit) {
        val query = dataRef.orderByChild(fieldName).equalTo(fieldValue)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val item = snapshot.getValue(ItemCart::class.java)
                    item?.let {
                        listItemCart.add(it)
                    }
                }
                callback(listItemCart)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Database error: ${databaseError.message}")
            }
        })
    }
}