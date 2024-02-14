package com.example.homeworktewentytwo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktewentytwo.databinding.ItemPictureRecyclerBinding
import com.example.homeworktewentytwo.databinding.PostItemRecyclerBinding
import com.example.homeworktewentytwo.presentation.extensions.loadImage
import com.example.homeworktewentytwo.presentation.model.PostUI

class PicturesRecyclerAdapter(private val images: List<String>) :
    RecyclerView.Adapter<PicturesRecyclerAdapter.PicturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val binding = ItemPictureRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PicturesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class PicturesViewHolder(private val binding: ItemPictureRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            binding.ivCover.loadImage(imageUrl)
        }
    }
}