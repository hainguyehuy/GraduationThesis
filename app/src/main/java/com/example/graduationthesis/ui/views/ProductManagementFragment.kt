package com.example.graduationthesis.ui.views

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.Notification
import com.example.graduationthesis.data.model.SamPlePD

import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.AddProductBinding
import com.example.graduationthesis.databinding.DialogChangepasswordBinding
import com.example.graduationthesis.databinding.FragmentProductManagementBinding
import com.example.graduationthesis.ui.adapters.ListProductManagementAdapter
import com.example.graduationthesis.ui.adapters.NotificationAdapter
import com.google.firebase.database.FirebaseDatabase


class ProductManagementFragment : Fragment() {
    private lateinit var binding : FragmentProductManagementBinding
    private val listPD = mutableListOf<SamPlePD>()
    private lateinit var adapter : ListProductManagementAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseDatabase.getInstance().getReference("ManagementProduct").child("ChildManager")
            .get().addOnSuccessListener {
                for (Doc in it.children) {
                    Doc.getValue(SamPlePD::class.java)?.let { it1 -> listPD.add(it1) }
                }
                binding.rvListM.layoutManager = LinearLayoutManager(context)
                binding.rvListM.setHasFixedSize(true)
                adapter = ListProductManagementAdapter(listPD)
                binding.rvListM.adapter = adapter
            }
        binding.addProduct.setOnClickListener {
            showDialogAddPD()
        }

    }

    private fun showDialogAddPD() {
        val dialogBiding = AddProductBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBiding.root)
            .create()
        dialogBiding.btnADD.setOnClickListener {
            TODO()
        }
        dialog.show()
        dialogBiding.tvAdd.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductManagementBinding.inflate(inflater,container,false)
        return binding.root
    }


}