package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.Address
import com.example.graduationthesis.data.model.Category
import com.example.graduationthesis.databinding.ItemCategoryBinding

class ParentProductAdapter( ) :RecyclerView.Adapter<ParentProductAdapter.ParentProductViewHolder>() {
    private val parentProducts = ArrayList<Category>()

    fun updateProducts(productList : List<Category>){
        this.parentProducts.clear()
        this.parentProducts.addAll(productList)
        notifyDataSetChanged()
    }
    inner class ParentProductViewHolder(val binding : ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentProductViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ParentProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parentProducts.size
    }

    override fun onBindViewHolder(holder: ParentProductViewHolder, position: Int) {
        val parentProduct = parentProducts[position]
        holder.binding.tvNameCategory.text = parentProduct.name
        val childProductAdapter = ChildProductAdapter(parentProduct.Products)
        holder.binding.recyclerView.adapter = childProductAdapter
    }

}