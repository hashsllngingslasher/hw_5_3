package com.example.hw_5_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.hw_5_3.databinding.ItemImageBinding

class PixaAdapter(var list: ArrayList<ImageModel>) : Adapter<PixaAdapter.PixaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun addList(listImage: ArrayList<ImageModel>){
        list.addAll(listImage)
    }

    class PixaViewHolder(val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.itemImage.load(model.largeImageURL)
        }
    }
}