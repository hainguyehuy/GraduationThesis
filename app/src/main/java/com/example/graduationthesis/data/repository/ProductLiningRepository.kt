package com.example.graduationthesis.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class ProductLiningRepository {
    private val database : DatabaseReference = FirebaseDatabase.getInstance().getReference("SampleCate")

    @Volatile private var INSTANCE : ProductLiningRepository?= null

    fun getInstance() : ProductLiningRepository {
        return INSTANCE ?: synchronized(this){
            val instance = ProductLiningRepository()
            INSTANCE = instance
            instance
        }
    }
    fun loadDataProduct(_categoryList : MutableLiveData<List<CategorysLining>>){
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    var gson = Gson()
                    val json = Gson().toJson(snapshot.value)
                    val data  = gson.fromJson(json, CategorysLining::class.java)
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