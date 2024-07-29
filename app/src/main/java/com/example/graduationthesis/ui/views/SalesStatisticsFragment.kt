package com.example.graduationthesis.ui.views

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.graduationthesis.databinding.FragmentSalesStatisticsBinding
import com.example.graduationthesis.ui.GUI.OrderPDAdminActivity
import com.example.graduationthesis.utils.toCurrency
import com.google.firebase.database.FirebaseDatabase
import java.util.Locale


class SalesStatisticsFragment : Fragment() {
    private lateinit var binding : FragmentSalesStatisticsBinding
    val dataRef = FirebaseDatabase.getInstance().getReference("CartProduct")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSalesStatisticsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvStartDate.setOnClickListener {
            showDatePickerDialog(binding.editTextStartDate)
        }
        binding.tvEndDate.setOnClickListener {
            showDatePickerDialog(binding.editTextEndDate)
        }
        binding.buttonCalculate.setOnClickListener {
            val startDateStr = binding.editTextStartDate.text.toString().trim()
            val endDateStr = binding.editTextEndDate.text.toString().trim()

            if (startDateStr.isNotEmpty() && endDateStr.isNotEmpty()) {
                calculateTotalAmount(startDateStr, endDateStr)
            } else {
                Toast.makeText(context, "Please select both start and end dates", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnWatchOrderPD.setOnClickListener {
            startActivity(Intent(context,OrderPDAdminActivity::class.java))
        }
    }

    private fun calculateTotalAmount(startDateStr: String, endDateStr: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            val startDate = dateFormat.parse(startDateStr)
            val endDate = dateFormat.parse(endDateStr)
            if (startDate != null && endDate != null) {
                fetchTotalAmountFromFirebase(startDate.time, endDate.time)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Invalid date format", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchTotalAmountFromFirebase(startTime: Long, endTime: Long) {
        dataRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var totalAmount = 0.0
                for (snapshot in task.result.children) {
                    val amount = snapshot.child("priceItemCart").getValue(Double::class.java)
                    if (amount != null) {
                        totalAmount += amount
                    }
                }
                binding.textViewResult.text = totalAmount.toCurrency()
            }
            else{
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun showDatePickerDialog(edtDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = context?.let {
            DatePickerDialog(it, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                edtDate.setText(selectedDate)
            }, year, month, day)
        }

        datePickerDialog!!.show()
    }

}