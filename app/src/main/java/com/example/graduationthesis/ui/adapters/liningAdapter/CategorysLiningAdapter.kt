package com.example.graduationthesis.ui.adapters.liningAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.lining.CategoryLining
import com.example.graduationthesis.data.model.lining.CategorysLining
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.FragmentCategoryBinding

class CategorysLiningAdapter(private val onItemClick: (ProductLining) -> Unit) :
    RecyclerView.Adapter<CategorysLiningAdapter.CategorysViewHolder>() {
    private val categoryList = ArrayList<CategorysLining>()

    fun updateCategorys(categoryList: List<CategorysLining>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorysViewHolder {
        val binding =
            FragmentCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategorysViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategorysViewHolder, position: Int) {
        val current = categoryList[position]
        val parentProductAdapter = ParentProductLiningAdapter(current.Category, onItemClick)
        holder.binding.rvMainProduct.adapter = parentProductAdapter

    }

    inner class CategorysViewHolder(val binding: FragmentCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}