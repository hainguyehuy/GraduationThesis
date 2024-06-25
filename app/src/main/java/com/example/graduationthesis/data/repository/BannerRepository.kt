package com.example.graduationthesis.data.repository

import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Banner

class BannerRepository {
    private val banners = arrayListOf<Banner>()

    init {
        banners.add(Banner(R.drawable.cart))
        banners.add(Banner(R.drawable.home))
        banners.add(Banner(R.drawable.cate))
        banners.add(Banner(R.drawable.notif))
        banners.add(Banner(R.drawable.acc))
    }
    fun getBanners() = banners
}