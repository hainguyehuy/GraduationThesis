package com.example.graduationthesis.ui.views

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.FragmentSalesStatisticsBinding
import java.util.Locale


class SalesStatisticsFragment : Fragment() {
    private lateinit var binding : FragmentSalesStatisticsBinding


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
    }

    private fun calculateTotalAmount(startDateStr: String, endDateStr: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            val startDate = dateFormat.parse(startDateStr)
            val endDate = dateFormat.parse(endDateStr)
            if (startDate != null && endDate != null) {
//                fetchTotalAmountFromFirebase(startDate.time, endDate.time)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Invalid date format", Toast.LENGTH_SHORT).show()
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