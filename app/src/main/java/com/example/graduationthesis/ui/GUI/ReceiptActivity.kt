package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.ReceiptPD
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.Supplie
import com.example.graduationthesis.databinding.ActivityReceiptBinding
import com.example.graduationthesis.ui.adapters.ReceiptAdapter
import com.example.graduationthesis.ui.adapters.SupplierAdapter
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReceiptBinding
    private lateinit var adapter : ReceiptAdapter
    private val listReceipt = mutableListOf<ReceiptPD>()
    val destinationRef = FirebaseDatabase.getInstance().getReference("ManagementProduct").child("ChildManager")
    val dataRef = FirebaseDatabase.getInstance().getReference("ReceiptProduct")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("nameSL")
        binding.tvNameSl.text = StringBuilder().append("Tên nhà cung cấp: $name")
        dataRef.get().addOnSuccessListener {
            for (Doc in it.children) {
                Doc.getValue(ReceiptPD::class.java)?.let { it1 -> listReceipt.add(it1) }
            }
            binding.rvSupplier.layoutManager = LinearLayoutManager(this)
            binding.rvSupplier.setHasFixedSize(true)
            adapter = ReceiptAdapter(){
                var totals = 0.0
                it.forEach {
                    totals += (it.pricePD*it.quantityOfGoods)
                }
                binding.totals.text = StringBuilder().append("Tổng tiền: ${totals.toCurrency()}")
            }
            binding.rvSupplier.adapter = adapter
            adapter.updateRPD(listReceipt)
        }
        binding.confirm.setOnClickListener {
            copyData()
        }
    }

    private fun copyData() {
        dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (userSnapshot in snapshot.children) {
                    val data = userSnapshot.getValue(SamPlePD::class.java)
                    data?.let {
                        destinationRef.child(data.idPD).setValue(data)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(applicationContext,"Thêm vào kho hàng thành công",Toast.LENGTH_SHORT).show()
                                } else {
                                    task.exception?.let {
                                        Toast.makeText(applicationContext,"Failed to copy data ${data.idPD}: ${it.message}",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read data from source table: ${error.message}")
            }
        })
    }
}