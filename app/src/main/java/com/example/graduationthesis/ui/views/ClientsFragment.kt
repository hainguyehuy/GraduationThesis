package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Notification
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.FragmentClientsBinding
import com.example.graduationthesis.ui.adapters.ItemClientAdapter
import com.example.graduationthesis.ui.adapters.NotificationAdapter
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClientsFragment : Fragment() {
    private lateinit var binding : FragmentClientsBinding
    private val listClient = mutableListOf<User>()
    private lateinit var adapterClient : ItemClientAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().getReference("User")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    val userData = Doc.getValue(User::class.java)
                    if(userData != null && userData.status =="user")
                    {
                        userData?.let { it1 -> listClient.add(it1) }
                    }
                }
                binding.rvClient.layoutManager = LinearLayoutManager(context)
                binding.rvClient.setHasFixedSize(true)
                adapterClient = ItemClientAdapter(listClient)
                binding.rvClient.adapter = adapterClient
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClientsBinding.inflate(inflater,container,false)
        return binding.root
    }


}