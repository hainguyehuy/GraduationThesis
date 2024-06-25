package com.example.graduationthesis.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
                    val productList :List<Category> = snapshot.children.map {
                            dataSnapshot -> dataSnapshot.getValue(Category::class.java)!!
                    }
                    _productList.postValue(productList)
                }catch (e: Exception ){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}