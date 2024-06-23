package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.graduationthesis.data.model.Banner
import com.example.graduationthesis.databinding.FragmentHomeBinding
import com.example.graduationthesis.ui.viewModel.BannerViewModel


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : BannerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
        viewModel.banner.observe(this, Observer { banner ->
            binding.imgBanner.setImageDrawable(
                ResourcesCompat.getDrawable(resources,banner.imgBanner,activity?.theme)
            )

        })
    }
}