package com.example.graduationthesis.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.FragmentPayBinding


class PayFragment : Fragment() {

    private lateinit var binding : FragmentPayBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkBox2.setOnClickListener {
            binding.linearInFo.visibility = ImageView.VISIBLE
            uncheckOtherCheckBoxes(checkBox = binding.checkBox2)
        }
        binding.checkBox1.setOnClickListener {
            Toast.makeText(context, "Thanh toán thành công", Toast.LENGTH_SHORT).show()
            binding.linearInFo.visibility = ImageView.GONE
            uncheckOtherCheckBoxes(checkBox = binding.checkBox1)
        }
    }
    private fun uncheckOtherCheckBoxes(checkBox: CheckBox) {
        if (checkBox != binding.checkBox2) {
            binding.checkBox2.isChecked = false
        }
        if (checkBox != binding.checkBox1) {
            binding.checkBox1.isChecked = false
        }

    }
}