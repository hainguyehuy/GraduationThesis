package com.example.graduationthesis.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.Category
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.data.model.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ProductRepository {
    private val database : DatabaseReference = FirebaseDatabase.getInstance().getReference("Category")

    @Volatile private var INSTANCE : ProductRepository?= null

    fun getInstance() : ProductRepository {
        return INSTANCE ?: synchronized(this){
            val instance = ProductRepository()
            INSTANCE = instance
            instance
        }

    }
    fun loadDataProduct(_productList : MutableLiveData<List<Category>>){
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    var gson = Gson()
                    val json = Gson().toJson(snapshot.value)
                    val data = listOf(gson.fromJson(json, Category::class.java))
                    _productList.postValue(data)
                } catch (e: Exception) {
                    Log.e("data", e.message.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}