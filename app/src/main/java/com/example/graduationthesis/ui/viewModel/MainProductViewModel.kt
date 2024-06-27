package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.Category
import com.example.graduationthesis.data.repository.ProductRepository

class MainProductViewModel : ViewModel() {
    private val _parentCategory = MutableLiveData<List<Category>>()
    val parentCategory : LiveData<List<Category>> = _parentCategory
    private val productRepository : ProductRepository
    init {
        productRepository = ProductRepository().getInstance()
        productRepository.loadDataProduct(_parentCategory)
    }
}