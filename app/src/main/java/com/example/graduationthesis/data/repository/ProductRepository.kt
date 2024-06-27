package com.example.graduationthesis.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class ProductRepository {
    private val database : DatabaseReference = FirebaseDatabase.getInstance().getReference("SampleCate")

    @Volatile private var INSTANCE : ProductRepository?= null

    fun getInstance() : ProductRepository {
        return INSTANCE ?: synchronized(this){
            val instance = ProductRepository()
            INSTANCE = instance
            instance
        }
    }
    fun loadDataProduct(_categoryList : MutableLiveData<List<Category>>){
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    var gson = Gson()
                    val json = Gson().toJson(snapshot.value)
                    val data  = gson.fromJson(json, Category::class.java)
                    _categoryList.postValue(listOf(data))
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