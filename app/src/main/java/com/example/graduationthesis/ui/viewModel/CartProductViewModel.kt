package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.data.repository.CartProductRepository

class CartProductViewModel() : ViewModel() {
    private val repository : CartProductRepository = CartProductRepository().getInstance()
//    private val _allCart = MutableLiveData<List<ItemCart>>()
//    val allCart : LiveData<List<ItemCart>> = _allCart
        val listCartData = MutableLiveData<List<ItemCart>>()
    init{
        repository.loadCartData()
    }
}