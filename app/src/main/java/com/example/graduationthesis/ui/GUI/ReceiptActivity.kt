package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.databinding.ActivityReceiptBinding
import com.example.graduationthesis.ui.adapters.ReceiptAdapter

class ReceiptActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReceiptBinding
    private lateinit var adapter : ReceiptAdapter
    private val listReceipt = mutableListOf<SamPlePD>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("nameSL")
        binding.tvNameSl.text = StringBuilder().append("Tên nhà cung cấp: $name")

    }
}