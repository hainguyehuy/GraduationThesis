package com.example.graduationthesis.ui.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.graduationthesis.R
import com.example.graduationthesis.ui.adapters.ImageAdapter
import com.example.graduationthesis.databinding.ActivityBeginBinding
import com.example.graduationthesis.data.model.ImgItem
import java.util.UUID

class BeginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBeginBinding

    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(10,0,10,0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDangNhap.setOnClickListener{
                it -> val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnDangKy.setOnClickListener{
                it -> val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        val imageList = arrayListOf(
            ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen1
            ),
            ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen
            ),
            ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen4
            ),
            ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen5
            ),
        )
        val imageAdapter = ImageAdapter()
        binding.viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)


        val dotImage = Array(imageList.size){
            ImageView(this)
        }

        dotImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            binding.slider.addView(it,params)
        }

        //default first dot selected
        dotImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                dotImage.mapIndexed { index, imageView ->
                    if(position==index){
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    }
                    else{
                        imageView.setImageResource(
                            R.drawable.non_active_dot
                        )
                    }
                }
                super.onPageSelected(position)
            }
        }
        binding.viewpager2.registerOnPageChangeCallback(pageChangeListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewpager2.unregisterOnPageChangeCallback(pageChangeListener)
    }

}