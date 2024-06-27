package com.example.graduationthesis.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.ItemAddress
import com.example.graduationthesis.data.model.ListHotProduct
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class AddressRepository {
     private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Address")

    @Volatile private var INSTANCE : AddressRepository?= null

    fun getInstance() : AddressRepository {
        return INSTANCE ?: synchronized(this){
            val instance = AddressRepository()
            INSTANCE = instance
            instance
        }

    }
    fun loadAddressData(addressList : MutableLiveData<List<Address>>){
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    var gson = Gson()
                    val json = Gson().toJson(snapshot.value)
                    val data = gson.fromJson(json, Address::class.java)
                    addressList.postValue(listOf(data))
                }catch (e: Exception ){
                    Log.e("data",e.message.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
 }