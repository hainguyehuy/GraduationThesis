package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.HotProduct

class HotProductAdapter() : RecyclerView.Adapter<HotProductAdapter.HotCateViewHolder>() {

    private var hotCategory = ArrayList<HotProduct>()

    fun updateHPList(hpList : List<HotProduct>){
        this.hotCategory.clear()
        this.hotCategory.addAll(hpList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotCateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_hotproduct,parent,false)
        return HotCateViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return hotCategory.size
    }

    override fun onBindViewHolder(holder: HotCateViewHolder, position: Int) {
        val current = hotCategory[position]
        holder.titleHotProduct.text = current.titleHP
        holder.imgHotProduct.setImageResource(current.urlImg)

    }
    inner class HotCateViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleHotProduct : TextView = itemView.findViewById(R.id.tvTitleHotProduct)
        val imgHotProduct : ImageView = itemView.findViewById(R.id.imgHotProduct)

    }
}
