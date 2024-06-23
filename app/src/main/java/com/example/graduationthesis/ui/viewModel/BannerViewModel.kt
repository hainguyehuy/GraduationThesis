package com.example.graduationthesis.ui.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Banner
import com.example.graduationthesis.data.repository.BannerRepository

class BannerViewModel {
    private val _banner = MutableLiveData<Banner>()
    val banner : LiveData<Banner> = _banner
    private val banners = BannerRepository().getBanners()
    private var currentIndex = 0
    private val delay = 3000L

    init {
        loopMovies()
    }


    fun updateBanner(){
        currentIndex++
        currentIndex %= banners.size

        _banner.value = banners[currentIndex]
        loopMovies()
    }

    private fun loopMovies() {
        android.os.Handler(Looper.getMainLooper()).postDelayed({updateBanner()},delay)
    }

}