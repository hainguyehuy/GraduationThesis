package com.example.graduationthesis.ui.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationthesis.databinding.FragmentAccountBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
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
    private lateinit var binding : FragmentAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun fetchDataUser() {
        FirebaseDatabase.getInstance().getReference("User")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val nameUser = snapshot.child("name").getValue(String::class.java)
                    binding.name.text = nameUser.toString()
                    val genderUser = snapshot.child("gender").getValue(String::class.java)
                    binding.gender.text = genderUser.toString()
                    val dateBirthUser = snapshot.child("dateBirht").getValue(String::class.java)
                    binding.date.text = dateBirthUser.toString()
                    val phoneNumberUser = snapshot.child("numberPhone").getValue(String::class.java)
                    binding.phone.text = phoneNumberUser.toString()
                    val addressUser = snapshot.child("address").getValue(String::class.java)
                    binding.address.text = addressUser.toString()
                    val emailUser = snapshot.child("email").getValue(String::class.java)
                    binding.email.text = emailUser.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("AccountFragment","Failed to read data user", error.toException())
                }

            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchDataUser()

    }
}