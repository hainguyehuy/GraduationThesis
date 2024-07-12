package com.example.graduationthesis.ui.adapters.adapterParentBrand

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Brand
import com.example.graduationthesis.data.model.ListBrand

class ChildBrandAdapter(private val listChildBrand : List<Brand>,val onClickItem :((Brand) -> Unit)) : RecyclerView.Adapter<ChildBrandAdapter.ChildBrandViewHolder>() {

    inner class ChildBrandViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imgBrand : ImageView = itemView.findViewById(R.id.imgBrand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildBrandViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand,parent,false)
        return ChildBrandViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listChildBrand.size
    }

    override fun onBindViewHolder(holder: ChildBrandViewHolder, position: Int) {
        holder.imgBrand.setImageResource(listChildBrand[position].imgBrand)
        holder.itemView.setOnClickListener {
            onClickItem(listChildBrand[position])
        }
    }
}