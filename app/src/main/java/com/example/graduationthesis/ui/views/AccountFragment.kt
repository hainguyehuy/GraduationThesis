package com.example.graduationthesis.ui.views

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.DialogChangepasswordBinding
import com.example.graduationthesis.databinding.FragmentAccountBinding
import com.example.graduationthesis.ui.GUI.LoginActivity
import com.example.graduationthesis.ui.GUI.OrderProductActivity
import com.example.graduationthesis.ui.GUI.TruckActivity
import com.example.graduationthesis.ui.GUI.WaitOrderActivity
import com.example.graduationthesis.ui.GUI.WaitconfirmOrderActivity
import com.example.graduationthesis.ui.GUI.userSignIn
import com.google.firebase.database.DatabaseReference


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
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
//            Glide.with(binding.imageView.context).load(it.imgUser)
//                .into(binding.imageView)
            binding.tvHello.text = StringBuilder().append("Xin chào: ${it.name}")
        }
        binding.tvInfo.setOnClickListener {
            binding.info.visibility = View.VISIBLE
            binding.statusOrder.visibility = View.GONE
        }
        binding.tvPass.setOnClickListener {
            binding.info.visibility = View.GONE
            showDialog()
        }
        binding.tvLogOut.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.tvOrderPD.setOnClickListener {
            binding.statusOrder.visibility = View.VISIBLE
            binding.info.visibility = View.GONE
            onClickItem()
        }
    }

    private fun onClickItem() {
        binding.waitconfirm.setOnClickListener {
            startActivity(Intent(context, WaitconfirmOrderActivity::class.java))
        }
        binding.waitOrder.setOnClickListener {
            startActivity(Intent(context, WaitOrderActivity::class.java))
        }
        binding.truck.setOnClickListener {
            startActivity(Intent(context, TruckActivity::class.java))
        }
        binding.history.setOnClickListener {
            startActivity(Intent(context, OrderProductActivity::class.java))
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