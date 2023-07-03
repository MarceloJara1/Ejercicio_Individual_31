package com.example.ejercicioindividual31

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual31.databinding.ItemRecyclerBinding

class UserAdapter(private var userList: List<User>): RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = userList.size

    fun setData(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()
    }

    fun getUserList(): List<User> {
        return userList
    }

}