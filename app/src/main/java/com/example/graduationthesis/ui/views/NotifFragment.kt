package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.HotProduct
import com.example.graduationthesis.data.model.Notification
import com.example.graduationthesis.databinding.FragmentNotifBinding
import com.example.graduationthesis.ui.adapters.HotProductAdapter
import com.example.graduationthesis.ui.adapters.NotificationAdapter
import com.google.firebase.database.FirebaseDatabase


class NotifFragment : Fragment() {
    private lateinit var binding : FragmentNotifBinding
    private val listNotif = mutableListOf<Notification>()
    private lateinit var adapterNotif : NotificationAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseDatabase.getInstance().getReference("Notification").child("ChildNotification")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    Doc.getValue(Notification::class.java)?.let { it1 -> listNotif.add(it1) }
                }
                binding.rvNotif.layoutManager = LinearLayoutManager(context)
                binding.rvNotif.setHasFixedSize(true)
                adapterNotif = NotificationAdapter(listNotif)
                binding.rvNotif.adapter = adapterNotif
            }
    }
}