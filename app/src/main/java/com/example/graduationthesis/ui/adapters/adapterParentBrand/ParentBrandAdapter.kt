package com.example.graduationthesis.ui.adapters.adapterParentBrand

import android.icu.lang.UCharacter.VerticalOrientation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.contentValuesOf
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationthesis.R
import com.example.graduationthesis.data.model.Brand
import com.example.graduationthesis.data.model.ListBrand
import com.example.graduationthesis.databinding.FragmentBeginCategoryBinding

class ParentBrandAdapter(private val listBrand : List<ListBrand>, private val onClickItem :((Brand) ->Unit)) : RecyclerView.Adapter<ParentBrandAdapter.ParentBrandViewHolder>() {

    inner class ParentBrandViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val brand : TextView = itemView.findViewById(R.id.tvBrand)
        val recyclerViewBrand : RecyclerView = itemView.findViewById(R.id.child_rv)
        val linearLayout : LinearLayout = itemView.findViewById(R.id.linear_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentBrandViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_brand,parent,false)
        return ParentBrandViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBrand.size
    }

    override fun onBindViewHolder(holder: ParentBrandViewHolder, position: Int) {
        val parentItem = listBrand[position]
        holder.brand.text = parentItem.title
        holder.recyclerViewBrand.setHasFixedSize(true)
        holder.recyclerViewBrand.layoutManager = GridLayoutManager(holder.itemView.context,3)

        val childAdapter = ChildBrandAdapter(parentItem.listBrand, onClickItem)
        holder.recyclerViewBrand.adapter = childAdapter

        val isExpandable = parentItem.isExpandable
        holder.recyclerViewBrand.visibility = if(isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            parentItem.isExpandable = !parentItem.isExpandable
            notifyItemChanged(position)
        }



    }
}