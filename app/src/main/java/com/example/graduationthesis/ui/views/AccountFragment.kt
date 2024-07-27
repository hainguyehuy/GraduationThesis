package com.example.graduationthesis.ui.views

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.DialogChangepasswordBinding
import com.example.graduationthesis.databinding.FragmentAccountBinding
import com.example.graduationthesis.ui.GUI.LoginActivity
import com.example.graduationthesis.ui.GUI.userSignIn
import com.google.firebase.auth.FirebaseAuth
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userSignIn?.let {
            binding.name.text = it.name
            binding.gender.text = it.gender
            binding.date.text = it.dateBirth
            binding.phone.text = it.numberPhone
            binding.address.text = it.address
            binding.email.text = it.email
            Glide.with(binding.imageView.context).load(it.imgUser)
                .into(binding.imageView)
        }
        binding.changePassWord.setOnClickListener {
            showDialog()
        }
        binding.logOut.setOnClickListener {
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog() {
        val dialogBiding = DialogChangepasswordBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(context)
            .setView(dialogBiding.root)
            .create()
        val newPass = dialogBiding.edtPWNEW.text.toString()
        val confirmNewPass = dialogBiding.edtPWNEW.text.toString()
        dialogBiding.buttonSubmit.setOnClickListener {
            updateData()
        }
        dialog.show()
        dialogBiding.tvClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun updateData() {
        userSignIn?.let {

        }
    }


}