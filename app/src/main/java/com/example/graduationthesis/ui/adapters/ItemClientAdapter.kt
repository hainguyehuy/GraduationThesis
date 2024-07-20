package com.example.graduationthesis.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationthesis.data.model.User
import com.example.graduationthesis.databinding.ListClientBinding

class ItemClientAdapter(private val listClient: List<User>) :
    RecyclerView.Adapter<ItemClientAdapter.ClientViewHolder>() {

    inner class ClientViewHolder(private val binding: ListClientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(user: User) {
            binding.tvNameClient.text = StringBuilder().append("Họ tên: ${user.name}")
            binding.tvEmailCL.text = StringBuilder().append("Email: ${user.email}")
            binding.tvAddressCL.text = StringBuilder().append("Địa chỉ: ${user.address}")
            binding.tvGenderCL.text = StringBuilder().append("Giới tính: ${user.gender}")
            binding.tvDateBirthCL.text = StringBuilder().append("Ngày sinh: ${user.dateBirht}")
            binding.tvPhoneNumberCL.text = StringBuilder().append("Số điện thoại: ${user.numberPhone}")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(
            ListClientBinding.inflate(
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