package com.example.graduationthesis.data.repository

import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Banner

class BannerRepository {
    private val banners = arrayListOf<Banner>()

    init {
        banners.add(Banner(R.drawable.b1))
        banners.add(Banner(R.drawable.b2))
        banners.add(Banner(R.drawable.b3))
        banners.add(Banner(R.drawable.b4))

    }
    fun getBanners() = banners
}