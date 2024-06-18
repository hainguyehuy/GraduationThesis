package com.example.graduationthesis.repository

import android.provider.CalendarContract.Instances
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.dataClass.Address
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddressRepository {
     private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("address")

    @Volatile private var INSTANCE : AddressRepository ?= null

    fun getInstance() : AddressRepository{
        return INSTANCE ?: synchronized(this){
            val instance = AddressRepository()
            INSTANCE = instance
            instance
        }

    }
    fun loadData(addressList : MutableLiveData<List<Address>>){
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                        val _addressList :List<Address> = snapshot.children.map {
                            dataSnapshot -> dataSnapshot.getValue(Address ::class.java)!!
                        }
                    addressList.postValue(_addressList)
                }catch (e: Exception ){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
 }