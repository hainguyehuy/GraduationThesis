package com.example.graduationthesis.ui.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.FragmentAccountBinding
import com.example.graduationthesis.ui.GUI.LoginActivity
import com.example.graduationthesis.ui.GUI.userSignIn
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var database: DatabaseReference
    private lateinit var user : User
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun fetchDataUser(status: Boolean) {
//        FirebaseDatabase.getInstance().getReference("User")
//            .addValueEventListener(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val nameUser = snapshot.child("name").getValue(String::class.java)
//                    binding.name.text = nameUser.toString()
//                    val genderUser = snapshot.child("gender").getValue(String::class.java)
//                    binding.gender.text = genderUser.toString()
//                    val dateBirthUser = snapshot.child("dateBirht").getValue(String::class.java)
//                   binding.gender.text = genderUser.toString()
//                    val phoneNumberUser = snapshot.child("numberPhone").getValue(String::class.java)
//                    binding.gender.text = genderUser.toString()
//                    val addressUser = snapshot.child("address").getValue(String::class.java)
//                    binding.gender.text = genderUser.toString()
//                    val emailUser = snapshot.child("email").getValue(String::class.java)
//                    binding.gender.text = genderUser.toString()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.e("AccountFragment","Failed to read data user", error.toException())
//                }
//
//            })
//        database.orderByChild("status").equalTo(status)
//            .addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        for (userSnapshot in snapshot.children) {
//                            val user = userSnapshot.getValue(User::class.java)
//
//                        }
//                    } else {
//
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Log.e("MainActivity", "Database error: ${error.message}")
//                }
//
//            })

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userSignIn?.let {
            binding.name.text = it.name
            binding.gender.text = it.gender
            binding.date.text = it.dateBirht
            binding.phone.text = it.numberPhone
            binding.address.text = it.address
            binding.email.text = it.email
        }
        binding.logOut.setOnClickListener {
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}