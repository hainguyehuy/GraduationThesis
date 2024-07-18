package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Brand
import com.example.graduationthesis.data.model.ListBrand
import com.example.graduationthesis.databinding.FragmentBeginCategoryBinding
import com.example.graduationthesis.ui.adapters.adapterParentBrand.ParentBrandAdapter
import com.example.graduationthesis.ui.views.CategoryFragment



/**
 * A simple [Fragment] subclass.
 * Use the [BeginCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeginCategoryFragment : Fragment() {
    private lateinit var adapter: ParentBrandAdapter
    private lateinit var listParentBrand: ArrayList<ListBrand>

    private lateinit var binding: FragmentBeginCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun fetchData() {
        val childItem = ArrayList<Brand>()
        childItem.add(Brand(R.drawable.yonex))
        childItem.add(Brand(R.drawable.victor))
        childItem.add(Brand(R.drawable.lining))
        childItem.add(Brand(R.drawable.kumpo))
        childItem.add(Brand(R.drawable.vs))
        childItem.add(Brand(R.drawable.mizuno))
        listParentBrand = arrayListOf()
        listParentBrand.add(ListBrand("Thương Hiệu", childItem,false))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeginCategoryBinding.inflate(inflater, container, false)
        binding.recyclerBrand.layoutManager = LinearLayoutManager(context)
        fetchData()
        adapter = ParentBrandAdapter(listParentBrand, ::onClickItem)
        binding.recyclerBrand.adapter = adapter

        return binding.root
    }
    private fun onClickItem(itemBrand : Brand){
            parentFragmentManager.beginTransaction()
                .replace(R.id.frmLayout, CategoryFragment())
                .addToBackStack(null)
                .commit()
    }

}