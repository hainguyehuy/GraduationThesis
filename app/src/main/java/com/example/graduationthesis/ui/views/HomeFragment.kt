package com.example.graduationthesis.ui.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.data.model.lining.CategoryLining
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.FragmentHomeBinding
import com.example.graduationthesis.ui.GUI.AddressActivity
import com.example.graduationthesis.ui.GUI.DetailProductLiningActivity
import com.example.graduationthesis.ui.adapters.HotProductAdapter
import com.example.graduationthesis.ui.adapters.liningAdapter.CategorysLiningAdapter
import com.example.graduationthesis.ui.adapters.liningAdapter.ChildProductLiningAdapter
import com.example.graduationthesis.ui.adapters.liningAdapter.ParentProductLiningAdapter
import com.example.graduationthesis.ui.viewModel.BannerViewModel
import com.example.graduationthesis.ui.viewModel.HotProductViewModel
import com.example.graduationthesis.ui.viewModel.ProductLiningViewModel
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: BannerViewModel
    private lateinit var viewModelHP: HotProductViewModel
    private lateinit var viewModelPDLining: ProductLiningViewModel
    private lateinit var adapter: HotProductAdapter
    private lateinit var adapterProductLining: CategorysLiningAdapter
    private lateinit var adapterChildProductLining: ChildProductLiningAdapter
    private var listHotProduct = mutableListOf<HotProduct>()
    private var listProductLining = mutableListOf<ProductLining>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.imgAddressShop.setOnClickListener {
            var intent = Intent(context, AddressActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseDatabase.getInstance().getReference("HotProduct").child("ChildHotProduct")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    Doc.getValue(HotProduct::class.java)?.let { it1 -> listHotProduct.add(it1) }
                }
                adapter = HotProductAdapter(listHotProduct)
                binding.rvItemHotProduct.adapter = adapter
            }
        adapterProductLining = CategorysLiningAdapter(::onItemClick)
        binding.rvItemCate.adapter = adapterProductLining

        viewModelPDLining = ViewModelProvider(this)[ProductLiningViewModel::class.java]
        viewModelPDLining.parentCategory.observe(viewLifecycleOwner, Observer {
            adapterProductLining.updateCategorys(it)
        })
//        binding.searchAddress.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                val searchText = newText!!.lowercase(Locale.getDefault())
//                if (searchText.isNotEmpty()){
//                    adapterChildProductLining = ChildProductLiningAdapter(listProductLining, onItemClick )
//                    adapterProductLining.updateCategorys(listProductLining.filter {
//                          it->
//                    })
//                }
//                else
//                {
//                    listPD.clear()
//                    listPD.addAll(newListPD)
//                    adapter.updatePD(listPD)
//                }
//                return false
//            }
//
//        })
    }

    private fun onItemClick(itemResponse: ProductLining) {
        var intent = Intent(context, DetailProductLiningActivity::class.java).apply {
            putExtra("namePD", itemResponse.namePD)
            putExtra("urlPD", itemResponse.urlPD)
            putExtra("pricePD", itemResponse.pricePD)
            putExtra("colorPD", itemResponse.colorPD)
            putExtra("titlePD", itemResponse.titlePD)
            putExtra("sizePD", itemResponse.sizePD)
        }
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        viewModel = BannerViewModel()
        viewModel.getBanner().observe(this, Observer { banner ->
            binding.imgBanner.setImageDrawable(
                ResourcesCompat.getDrawable(resources, banner.imgBanner, activity?.theme)
            )

        })
    }
}