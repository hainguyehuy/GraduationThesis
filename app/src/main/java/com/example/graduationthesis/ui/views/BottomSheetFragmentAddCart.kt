package com.example.graduationthesis.ui.views


import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.ItemCart

import com.example.graduationthesis.databinding.FragmentBottomSheetAddCartBinding
import com.example.graduationthesis.utils.toCurrency
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.NumberFormat
import kotlin.text.StringBuilder


class BottomSheetFragmentAddCart(private var price: Double) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetAddCartBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetAddCartBinding.inflate(inflater, container, false)
        val namePD = arguments?.getString("namePD")
        binding.tvNameProduct.text = namePD
        val colorPD = arguments?.getString("colorPD")
        binding.tvColorProduct.text = colorPD
        val pricePD = arguments?.getString("pricePD")
        binding.tvPriceProduct.text = pricePD
        val urlPD = arguments?.getString("urlPD")
        if (urlPD != null) {
            val img = Uri.parse(urlPD)
            Glide.with(this)
                .load(img)
                .into(binding.imgProDuctBT)
        }

        //handle click checkboxColor
        binding.red.setOnCheckedChangeListener { _, isChecked
            ->
            if (isChecked) {
                uncheckOtherCheckBoxes(binding.red)
                binding.tvColorProduct.text = StringBuilder().append("Màu sắc: ${binding.red.text}")
                binding.tvChoseColor.text = StringBuilder().append("Màu sắc: ${binding.red.text}")
            } else {
                ""
            }
        }
        binding.blue.setOnCheckedChangeListener { _, isChecked
            ->
            if (isChecked) {
                uncheckOtherCheckBoxes(binding.blue)
                binding.tvColorProduct.text =
                    StringBuilder().append("Màu sắc: ${binding.blue.text}")
                binding.tvChoseColor.text = StringBuilder().append("Màu sắc: ${binding.blue.text}")
            }
        }
        binding.white.setOnCheckedChangeListener { _, isChecked
            ->
            if (isChecked) {
                uncheckOtherCheckBoxes(binding.white)
                binding.tvColorProduct.text =
                    StringBuilder().append("Màu sắc: ${binding.white.text}")
                binding.tvChoseColor.text = StringBuilder().append("Màu sắc: ${binding.white.text}")
            }
        }
        //handle click checkboxSize
        binding.s39.setOnClickListener {
            binding.tvSizeProduct.text = StringBuilder().append("Kích thước: ${binding.s39.text}")
            binding.tvChoseSize.text = StringBuilder().append("Kích thước: ${binding.s39.text}")
        }
        binding.s40.setOnClickListener {

            binding.tvSizeProduct.text = StringBuilder().append("Kích thước: ${binding.s40.text}")
            binding.tvChoseSize.text = StringBuilder().append("Kích thước: ${binding.s40.text}")
        }
        binding.s41.setOnClickListener {

            binding.tvSizeProduct.text = StringBuilder().append("Kích thước: ${binding.s41.text}")
            binding.tvChoseSize.text = StringBuilder().append("Kích thước: ${binding.s41.text}")
        }
        binding.s42.setOnClickListener {

            binding.tvSizeProduct.text = StringBuilder().append("Kích thước: ${binding.s42.text}")
            binding.tvChoseSize.text = StringBuilder().append("Kích thước: ${binding.s42.text}")
        }
        binding.s43.setOnClickListener {

            binding.tvSizeProduct.text = StringBuilder().append("Kích thước: ${binding.s43.text}")
            binding.tvChoseSize.text = StringBuilder().append("Kích thước: ${binding.s43.text}")
        }
        //handle reduced and increase and reduced
        binding.minus.setOnClickListener {
            if (count > 0) {
                count--
                binding.tvCount.text = count.toString()
                binding.tvChillCount.text = StringBuilder().append("Số lượng: $count")
                updatePricePD()
            }
        }
        binding.plus.setOnClickListener {
            count++
            binding.tvCount.text = count.toString()
            binding.tvChillCount.text = StringBuilder().append("Số lượng: $count")
            updatePricePD()
        }
        //handle click button addCart
        binding.btnAddCart.setOnClickListener {
            saveItemCart()
        }
        binding.rightIcon.setOnClickListener {
           dismiss()
        }
        return binding.root
    }

    private fun saveItemCart() {

        val nameItemCart = binding.tvNameProduct.text.toString()
        val colorItemCart = binding.tvColorProduct.text.toString()
        val priceItemCart = price
        val sizeItemCart = binding.tvSizeProduct.text.toString()
        val urlItemCart = binding.imgProDuctBT

        try {
            database = FirebaseDatabase.getInstance().getReference("CartProduct").push()
            val itemCart = ItemCart(
                database.key.toString(),
                nameItemCart,
                colorItemCart,
                priceItemCart,
                sizeItemCart
            )
            database.setValue(itemCart)
            .addOnSuccessListener {
                Toast.makeText(context, "save data success", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(context, "save data fail", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Log.e("data", e.message.toString())
        }


    }

    private fun updatePricePD() {
        var pricePD = price
        var f = NumberFormat.getInstance()
        var doublePricePD: Double = f.parse("${pricePD}").toDouble()
        val totalPricePD = count * doublePricePD
        binding.tvPriceProduct.text = totalPricePD.toCurrency()
    }

    private fun uncheckOtherCheckBoxes(checkBox: CheckBox) {
        if (checkBox != binding.red) {
            binding.red.isChecked = false
        }
        if (checkBox != binding.blue) {
            binding.blue.isChecked = false
        }
        if (checkBox != binding.white) {
            binding.white.isChecked = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}