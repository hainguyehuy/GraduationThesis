package com.example.graduationthesis.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.HotProduct
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HPRepository {
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("HotProduct")

    @Volatile private var INSTANCE : HPRepository?= null

    fun getInstance() : HPRepository {
        return INSTANCE ?: synchronized(this){
            val instance = HPRepository()
            INSTANCE = instance
            instance
        }

    }
    fun loadHPData(hotProductList : MutableLiveData<List<HotProduct>>){
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _hpListList :List<HotProduct> = snapshot.children.map {
                            dataSnapshot -> dataSnapshot.getValue(HotProduct ::class.java)!!
                    }
                    hotProductList.postValue(_hpListList)
                }catch (e: Exception ){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}