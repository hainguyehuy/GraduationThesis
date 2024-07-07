package com.example.graduationthesis.ui.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.data.model.ItemAddress
import com.example.graduationthesis.databinding.ActivityAddressBinding
import com.example.graduationthesis.databinding.FragmentAddressBinding
import com.example.graduationthesis.ui.adapters.AddressAdapter
import com.example.graduationthesis.ui.adapters.HotProductAdapter
import com.example.graduationthesis.ui.adapters.ItemAddressAdapter
import com.example.graduationthesis.ui.viewModel.AddressViewModel
import com.google.firebase.database.FirebaseDatabase

class AddressActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddressBinding
    private var addressArrayList = mutableListOf <ItemAddress>()
    private lateinit var adapter: ItemAddressAdapter
    private lateinit var adapterItem: ItemAddressAdapter
    private lateinit var viewModel: AddressViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAddress.layoutManager = LinearLayoutManager(this)
        binding.rvAddress.setHasFixedSize(true)
        adapter = ItemAddressAdapter(addressArrayList)
        FirebaseDatabase.getInstance().getReference("Address") .child("ItemAddress")
            .get().addOnSuccessListener {
                for(Doc in it.children){
                    Doc.getValue(ItemAddress::class.java)?.let { it1 -> addressArrayList.add(it1) }
                }
                adapter = ItemAddressAdapter(addressArrayList)
                binding.rvAddress.adapter = adapter
            }
//        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
//        viewModel.allAddress.observe(viewLifecycleOwner, Observer {
//            adapter.updateAddressList(it)
//        })
    }
}