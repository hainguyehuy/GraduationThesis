package com.example.graduationthesis.ui.adapters.liningAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.lining.CategoryLining
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.ItemCategoryBinding

class ParentProductLiningAdapter(
    private val parentProducts: List<CategoryLining>,
    val onClickItem: ((ProductLining) -> Unit)
) : RecyclerView.Adapter<ParentProductLiningAdapter.ParentProductViewHolder>() {

    inner class ParentProductViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentProductViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return parentProducts.size
    }

    override fun onBindViewHolder(holder: ParentProductViewHolder, position: Int) {
        val parentProduct = parentProducts[position]
        holder.binding.tvNameCategory.text = parentProduct.name
        val childProductAdapter = ChildProductLiningAdapter(parentProduct.Products, onClickItem)
        holder.binding.recyclerView.adapter = childProductAdapter

    }

}