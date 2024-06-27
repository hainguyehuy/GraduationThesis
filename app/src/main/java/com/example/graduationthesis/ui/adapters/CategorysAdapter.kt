package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.Category
import com.example.graduationthesis.databinding.FragmentCategoryBinding

class CategorysAdapter(): RecyclerView.Adapter<CategorysAdapter.CategorysViewHolder>() {
    private val categorysList = ArrayList<Category>()

    fun updateCategorys(categoryList: List<Category>){
        this.categorysList.clear()
        this.categorysList.addAll(categoryList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorysViewHolder {
        val binding = FragmentCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategorysViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categorysList.size
    }

    override fun onBindViewHolder(holder: CategorysViewHolder, position: Int) {
        val current  = categorysList[position]
        val parentProductAdapter = ParentProductAdapter(current.Cate)
        holder.binding.rvMainProduct.adapter = parentProductAdapter

    }
    inner class CategorysViewHolder(val binding: FragmentCategoryBinding) : RecyclerView.ViewHolder(binding.root){

    }
}