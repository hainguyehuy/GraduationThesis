package com.example.graduationthesis.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.data.model.ListHotProduct
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

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
    fun loadHPData(hotProductList : MutableLiveData<List<ListHotProduct>>){
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    var gson = Gson()
                    val json = Gson().toJson(snapshot.value)
                    val data = gson.fromJson(json, ListHotProduct::class.java)
                    hotProductList.postValue(listOf(data))
                }catch (e: Exception ){
                    Log.e("error",e.message.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}