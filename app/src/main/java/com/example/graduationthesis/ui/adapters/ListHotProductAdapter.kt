package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.data.model.ListHotProduct
import com.example.graduationthesis.databinding.FragmentHomeBinding

class ListHotProductAdapter(): RecyclerView.Adapter<ListHotProductAdapter.ListHPViewHolder>() {
    private var listHotProduct = ArrayList<ListHotProduct>()

    inner class ListHPViewHolder(val binding : FragmentHomeBinding) : RecyclerView.ViewHolder(binding.root)

        fun updateLHPList(hpList : List<ListHotProduct>){
        this.listHotProduct.clear()
        this.listHotProduct.addAll(hpList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHPViewHolder {
        val binding = FragmentHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListHPViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listHotProduct.size
    }

    override fun onBindViewHolder(holder: ListHPViewHolder, position: Int) {

        val hotProductAdapter = HotProductAdapter(listHotProduct[position].ChildHotProduct)
        holder.binding.rvItemHotProduct.adapter = hotProductAdapter
    }
}