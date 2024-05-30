package com.example.graduationthesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private var imgs: MutableList<String>, private var tvs: MutableList<Int>):RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {
    inner class Pager2ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val img:ImageView = itemview.findViewById(R.id.imgPager)
        val tv:TextView = itemview.findViewById(R.id.tv1)

        init {
            img.setOnClickListener { v:View ->
                val position = adapterPosition
                Toast.makeText(itemview.context,"You click on item #${position+1}",Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.tv.text = tvs[position]
        holder.img.setImageResource(imgs[position])
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

}