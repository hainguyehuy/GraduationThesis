package com.example.graduationthesis.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.ui.adapters.ItemCartAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class CartProductRepository {
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("CartProduct")
    @Volatile private var INSTANCE : CartProductRepository?= null

    fun getInstance() : CartProductRepository {
        return INSTANCE ?: synchronized(this){
            val instance = CartProductRepository()
            INSTANCE = instance
            instance
        }
    }
    fun loadCartData(){
        val itemCartList = mutableListOf<ItemCart>()
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
              snapshot.children.forEach{
                  it.getValue(ItemCart::class.java)?.let { it1 -> itemCartList.add(it1) }
              }
                MainActivity.viewModelCart.listCartData.postValue(itemCartList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}