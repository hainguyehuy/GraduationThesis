package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.Notification
import com.example.graduationthesis.databinding.ItemNotifBinding

class NotificationAdapter(private val listNotif : List<Notification>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(val binding: ItemNotifBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itemNotifBinding: ItemNotifBinding){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotifBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNotif.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val itemNotification = listNotif[position]
        holder.binding.tvNameNotif.text = itemNotification.nameNT
        holder.binding.tvTitleNotif.text = itemNotification.titleNT
        holder.binding.tvTitle2.text = itemNotification.titleNT2
        holder.binding.tvDate.text = itemNotification.dateTimeNT
        Glide.with(holder.binding.imgNotif.context).load(itemNotification.imgNt)
            .into(holder.binding.imgNotif)
    }
}