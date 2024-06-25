package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Category
import com.example.graduationthesis.databinding.FragmentCategoryBinding
import com.example.graduationthesis.databinding.FragmentHomeBinding
import com.example.graduationthesis.ui.adapters.AddressAdapter
import com.example.graduationthesis.ui.adapters.ParentProductAdapter
import com.example.graduationthesis.ui.viewModel.AddressViewModel
import com.example.graduationthesis.ui.viewModel.MainProductViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    private lateinit var adapter: ParentProductAdapter
    private lateinit var viewModel: MainProductViewModel
    private lateinit var binding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMainProduct.layoutManager = LinearLayoutManager(context)
        binding.rvMainProduct.setHasFixedSize(true)

        adapter = ParentProductAdapter()
        binding.rvMainProduct.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainProductViewModel::class.java)
        viewModel.parentProduct.observe(viewLifecycleOwner, Observer {
            adapter.updateProducts(it)
        })

    }
}