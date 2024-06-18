package com.example.graduationthesis.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.adapters.AddressAdapter
import com.example.graduationthesis.dataClass.AddressViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private lateinit var viewModel : AddressViewModel
private lateinit var addressRecyclerView: RecyclerView
lateinit var adapter : AddressAdapter

class AddressFragment : Fragment() {

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
        addressRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        addressRecyclerView.setHasFixedSize(true)
        adapter = AddressAdapter()
        addressRecyclerView.adapter = adapter


        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)

        viewModel.allAddress.observe(viewLifecycleOwner, Observer {
            adapter.updateAddressList(it)
        })
    }
}