package com.example.ejercicioindividual31

import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual31.databinding.ItemRecyclerBinding

class UserViewHolder(private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User){
        binding.txtDatosUsuario.text = "${user.name} - ${user.userName} - ${user.age}"
    }

}
