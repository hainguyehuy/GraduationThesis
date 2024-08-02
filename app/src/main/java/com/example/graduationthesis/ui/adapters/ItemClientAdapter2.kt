package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.ListClient2Binding
import com.example.graduationthesis.databinding.ListClientBinding

class ItemClientAdapter2(private val listClient: List<User>) :
    RecyclerView.Adapter<ItemClientAdapter2.ClientViewHolder>() {

    inner class ClientViewHolder(private val binding: ListClient2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(user: User) {
            binding.tvNameClient.text = StringBuilder().append("Họ tên: ${user.name}")
            binding.tvEmailCL.text = StringBuilder().append("Email: ${user.email}")
            binding.tvAddressCL.text = StringBuilder().append("Địa chỉ: ${user.address}")
            binding.tvGenderCL.text = StringBuilder().append("Giới tính: ${user.gender}")
            binding.tvDateBirthCL.text = StringBuilder().append("Ngày sinh: ${user.dateBirth}")
            binding.tvPhoneNumberCL.text = StringBuilder().append("Số điện thoại: ${user.numberPhone}")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
            ListClient2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listClient.size
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.setData(listClient.get(position))
    }

}