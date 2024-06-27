package com.example.graduationthesis.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.data.model.ListHotProduct
import com.example.graduationthesis.databinding.FragmentHomeBinding
import com.example.graduationthesis.ui.adapters.HotProductAdapter
import com.example.graduationthesis.ui.adapters.ListHotProductAdapter
import com.example.graduationthesis.ui.viewModel.BannerViewModel
import com.example.graduationthesis.ui.viewModel.HotProductViewModel


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : BannerViewModel
    private lateinit var viewModelHP : HotProductViewModel
    private lateinit var adapterHP: ListHotProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.imgAddressShop.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frLayout, AddressFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.rvItemHotProduct.layoutManager = LinearLayoutManager(context)
//        binding.rvItemHotProduct.setHasFixedSize(true)
//        adapterHP = ListHotProductAdapter()
//
//        binding.rvItemHotProduct.adapter = adapterHP
//
//        viewModelHP = ViewModelProvider(this).get(HotProductViewModel::class.java)
//        viewModelHP.allHP.observe(viewLifecycleOwner, Observer {
//            adapterHP.updateLHPList(it)
//        })
    }
    override fun onResume() {
        super.onResume()
        viewModel = BannerViewModel()
        viewModel.getBanner().observe(this, Observer { banner ->
            binding.imgBanner.setImageDrawable(
                ResourcesCompat.getDrawable(resources,banner.imgBanner,activity?.theme)
            )

        })
    }
}