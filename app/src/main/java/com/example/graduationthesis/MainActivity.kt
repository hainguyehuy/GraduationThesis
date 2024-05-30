package com.example.graduationthesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.graduationthesis.models.ImgItem
import com.example.graduationthesis.adapters.ImageAdapter
import com.example.graduationthesis.views.LoginFragment
import com.example.graduationthesis.views.RegisterFragment
import java.util.UUID


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var slider :LinearLayout
    private lateinit var btnDangNhap: Button
    private lateinit var btnDangKy :Button
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback
    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(10,0,10,0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewpager2)
        slider = findViewById(R.id.slider)

        val imageList = arrayListOf(
            ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen
            ),
            ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen2
            ),ImgItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" +R.drawable.hinhnen3
            )
        )
        val imageAdapter = ImageAdapter()
        viewPager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        slider = findViewById(R.id.slider)
        val dotImage = Array(imageList.size){
            ImageView(this)
        }

        dotImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slider.addView(it,params)
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
        viewPager2.registerOnPageChangeCallback(pageChangeListener)


        btnDangNhap = findViewById(R.id.btnDangNhap)
        btnDangKy = findViewById(R.id.btnDangKy)

        btnDangNhap.setOnClickListener{
            it -> val intent = Intent(applicationContext, LoginFragment::class.java)
        }
        btnDangKy.setOnClickListener{
                it -> val intent = Intent(applicationContext, RegisterFragment::class.java)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(pageChangeListener)
    }

}