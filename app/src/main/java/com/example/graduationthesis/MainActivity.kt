package com.example.graduationthesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.graduationthesis.GUI.LoginActivity
import com.example.graduationthesis.GUI.SignUpActivity
import com.example.graduationthesis.models.ImgItem
import com.example.graduationthesis.adapters.ImageAdapter
import com.example.graduationthesis.databinding.ActivityMainBinding
import java.util.UUID


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}