package com.example.graduationthesis.data.repository

import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Banner

class BannerRepository {
    private val banners = arrayListOf<Banner>()

    init {
        banners.add(Banner(R.drawable.banner1))
        banners.add(Banner(R.drawable.banner2))
        banners.add(Banner(R.drawable.banner3))
        banners.add(Banner(R.drawable.banner4))
        banners.add(Banner(R.drawable.banner5))
    }
    fun getBanners() = banners
}