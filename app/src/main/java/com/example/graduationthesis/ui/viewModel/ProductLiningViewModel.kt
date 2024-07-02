package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.example.graduationthesis.data.repository.ProductLiningRepository

class ProductLiningViewModel : ViewModel() {
    private val _parentCategory = MutableLiveData<List<CategorysLining>>()
    val parentCategory : LiveData<List<CategorysLining>> = _parentCategory
    private val productRepository : ProductLiningRepository
    init {
        productRepository = ProductLiningRepository().getInstance()
        productRepository.loadDataProduct(_parentCategory)
    }
}