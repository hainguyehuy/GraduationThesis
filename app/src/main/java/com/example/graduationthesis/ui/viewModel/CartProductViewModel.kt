package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.repository.CartProductRepository

class CartProductViewModel() : ViewModel() {
    private val repository: CartProductRepository = CartProductRepository().getInstance()

    //    private val _allCart = MutableLiveData<List<ItemCart>>()
//    val allCart : LiveData<List<ItemCart>> = _allCart
    val listCartData = MutableLiveData<List<ItemCart>>()
    val countProduct = MutableLiveData<Int>()
    val totalPriceProduct = MutableLiveData<Double>()
    init {
        repository.loadCartData()
    }

    fun updateNumberProduct(count : Int){
        countProduct.postValue(count)
    }

    fun updatePriceProduct(total : Double){
        totalPriceProduct.postValue(total)
    }
}