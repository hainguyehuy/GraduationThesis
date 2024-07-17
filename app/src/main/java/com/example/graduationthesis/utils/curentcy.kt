package com.example.graduationthesis.utils

import java.text.NumberFormat
import java.util.Locale


    //format money
    fun Double.toCurrency(format: String = "vn"): String {
        val locale = when (format.lowercase()) {
            "us" -> Locale.US
            "uk" -> Locale.UK
            "vn" -> Locale("vi", "VN")
            // Add more formats as needed
            else -> Locale.getDefault()
        }

        val currencyFormat = NumberFormat.getCurrencyInstance(locale)
        return currencyFormat.format(this)
    }

    fun Int.toCurrency(format: String = "vn"): String {
        val locale = when (format.lowercase()) {
            "us" -> Locale.US
            "uk" -> Locale.UK
            "vn" -> Locale("vi", "VN")
            // Add more formats as needed
            else -> Locale.getDefault()
        }

        val currencyFormat = NumberFormat.getCurrencyInstance(locale)
        return currencyFormat.format(this)
    }

    fun Float.toCurrency(format: String = "vn"): String {
        val locale = when (format.lowercase()) {
            "us" -> Locale.US
            "uk" -> Locale.UK
            "vn" -> Locale("vi", "VN")
            else -> Locale.getDefault()
        }

        val currencyFormat = NumberFormat.getCurrencyInstance(locale)
        return currencyFormat.format(this)
    }
