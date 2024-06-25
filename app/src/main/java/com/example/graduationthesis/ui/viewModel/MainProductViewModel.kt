package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.Category
import com.example.graduationthesis.data.repository.ProductRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainProductViewModel : ViewModel() {
    private val _parentProduct = MutableLiveData<List<Category>>()
    val parentProduct : LiveData<List<Category>> = _parentProduct
    private val productRepository : ProductRepository
    init {
        productRepository = ProductRepository().getInstance()
        productRepository.loadDataProduct(_parentProduct)
    }
}