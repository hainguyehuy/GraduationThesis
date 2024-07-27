package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.FragmentAdminBinding
import com.example.graduationthesis.ui.adapters.ItemClientAdapter
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {
    private lateinit var binding : FragmentAdminBinding
    private val listClient = mutableListOf<User>()
    private lateinit var adapterClient : ItemClientAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().getReference("User")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    val userData = Doc.getValue(User::class.java)
                    if(userData != null && userData.status =="admin")
                    {
                        userData?.let { it1 -> listClient.add(it1) }
                    }
                }
                binding.rvAdmin.layoutManager = LinearLayoutManager(context)
                binding.rvAdmin.setHasFixedSize(true)
                adapterClient = ItemClientAdapter(listClient)
                binding.rvAdmin.adapter = adapterClient
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdminBinding.inflate(inflater,container,false)
        return binding.root
    }

}