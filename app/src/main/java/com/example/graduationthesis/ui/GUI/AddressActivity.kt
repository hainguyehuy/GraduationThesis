package com.example.graduationthesis.ui.GUI

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.ItemAddress
import com.example.graduationthesis.databinding.ActivityAddressBinding

import com.example.graduationthesis.ui.adapters.ItemAddressAdapter
import com.example.graduationthesis.ui.viewModel.AddressViewModel
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList
import java.util.Locale

class AddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddressBinding
    private var addressArrayList = mutableListOf<ItemAddress>()
    private lateinit var adapter: ItemAddressAdapter
    private lateinit var viewModel: AddressViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAddress.layoutManager = LinearLayoutManager(this)
        binding.rvAddress.setHasFixedSize(true)
        adapter = ItemAddressAdapter(addressArrayList)
        FirebaseDatabase.getInstance().getReference("Address").child("ItemAddress")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    Doc.getValue(ItemAddress::class.java)?.let { it1 -> addressArrayList.add(it1) }
                }
                adapter = ItemAddressAdapter(addressArrayList)
                binding.rvAddress.adapter = adapter
            }
//        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
//        viewModel.allAddress.observe(viewLifecycleOwner, Observer {
//            adapter.updateAddressList(it)
//        })

        // event Search address
        binding.searchAddress.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterList(newText)
                }
                return true
            }
        })

    }
    private fun filterList(text: String) {
        if (text != null) {
            val arrayListAddress = ArrayList<ItemAddress>()
            for (i in addressArrayList) {
                if (i.pNumberAddress?.lowercase(Locale.ROOT)?.contains(text) == true)
                    arrayListAddress.add(i)
            }
            if (arrayListAddress.isEmpty()) {
                Toast.makeText(this, "No Address Found", Toast.LENGTH_LONG).show()
            } else {
                adapter = ItemAddressAdapter(addressArrayList)
                adapter.setFilteredList(arrayListAddress)
            }
        }

    }

}
