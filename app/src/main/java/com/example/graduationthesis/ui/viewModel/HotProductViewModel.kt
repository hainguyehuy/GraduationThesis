package com.example.graduationthesis.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationthesis.data.model.ListHotProduct
import com.example.graduationthesis.data.repository.HPRepository

class HotProductViewModel : ViewModel() {
    private val repository : HPRepository
    private val _allHP = MutableLiveData<List<ListHotProduct>>()
    val allHP : LiveData<List<ListHotProduct>> = _allHP

    init{
        repository= HPRepository().getInstance()
        repository.loadHPData(_allHP)
    }
}