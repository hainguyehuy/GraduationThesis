package com.example.graduationthesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {
    private var tvList = mutableListOf<String>()
    private var imgList = mutableListOf<Int>()
    private lateinit var view_page2:ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postToList()
        view_page2 = findViewById(R.id.view_page2)
        view_page2.adapter = ViewPagerAdapter(tvList,imgList)
        view_page2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator3 = findViewById<CircleIndicator3>(R.id.indicator)
        indicator3.setViewPager(view_page2)



    }
    private fun addToList(tv:String,img:Int){
        tvList.add(tv)
        imgList.add(img)
    }
    private fun postToList(){
        for(i in 1..5){
            addToList("tv $i",R.mipmap.ic_launcher_round)
        }
    }

}