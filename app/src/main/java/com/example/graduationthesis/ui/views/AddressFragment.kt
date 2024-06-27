package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.ui.adapters.ItemAddressAdapter
import com.example.graduationthesis.data.model.ItemAddress
import com.example.graduationthesis.ui.adapters.AddressAdapter
import com.example.graduationthesis.ui.viewModel.AddressViewModel
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//private lateinit var viewModel : AddressViewModel


class AddressFragment : Fragment() {
    private lateinit var addressRecyclerView: RecyclerView
    private lateinit var searchView : androidx.appcompat.widget.SearchView
    private lateinit var addressArrayList: ArrayList<ItemAddress>
    private lateinit var adapter: AddressAdapter
    private lateinit var adapterItem: ItemAddressAdapter
    private lateinit var viewModel: AddressViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addressRecyclerView = view.findViewById(R.id.rvAddress)
        addressRecyclerView.layoutManager = LinearLayoutManager(context)
        addressRecyclerView.setHasFixedSize(true)
        adapter = AddressAdapter()
        addressRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        viewModel.allAddress.observe(viewLifecycleOwner, Observer {
            adapter.updateAddressList(it)
        })
        addressArrayList = arrayListOf<ItemAddress>()
//        getAddress()

        // event Search address
//        searchView = view.findViewById(R.id.searchAddress)
//        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null) {
//                    filterList(newText)
//                }
//                return true
//            }
//        })

    }

    private fun filterList(text: String) {
        if(text != null){
            val filteredList = ArrayList<ItemAddress>()
            for(i in addressArrayList){
                if(i.nameAddress?.lowercase(Locale.ROOT)?.contains(text) == true)
                    filteredList.add(i)
            }
            if(addressArrayList.isEmpty()){
                Toast.makeText(context,"No Address Found", Toast.LENGTH_LONG).show()
            }
            else{
                adapterItem = ItemAddressAdapter(addressArrayList)
                adapterItem.setFilteredList(filteredList)
            }
        }

    }

//    private fun getAddress() {
//        databaseReference  = FirebaseDatabase.getInstance().getReference("Address")
//        databaseReference.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    for (addressSnapshot in snapshot.children){
//                        val address = addressSnapshot.getValue(Address::class.java)
//                        addressArrayList.add(address!!)
//                    }
//                    addressRecyclerView.adapter = AddressAdapter(addressArrayList)
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        })
//    }
}