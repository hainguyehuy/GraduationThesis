package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.repository.AddressRepository

class AddressViewModel :ViewModel() {
    private val repository : AddressRepository
    private val _allAddress = MutableLiveData<List<Address>>()
    val allAddress : LiveData<List<Address>> = _allAddress

    init{
        repository= AddressRepository().getInstance()
        repository.loadData(_allAddress)
    }
}