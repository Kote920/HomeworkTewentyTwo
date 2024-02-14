package com.example.homeworktewentytwo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktewentytwo.databinding.StoryItemRecyclerBinding
import com.example.homeworktewentytwo.presentation.extensions.loadImage
import com.example.homeworktewentytwo.presentation.model.StoryUI

class StoriesRecyclerAdapter() :
    ListAdapter<StoryUI, StoriesRecyclerAdapter.TripsViewHolder>(StoriesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TripsViewHolder(
        StoryItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {
        holder.bind()
    }

    inner class TripsViewHolder(private val binding: StoryItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: StoryUI

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvTitle.text = model.title
                ivCover.loadImage(model.cover)

            }
            listeners()
        }

        private fun listeners() {
            binding.root.setOnClickListener {
            }
        }

    }

    class StoriesDiffUtil : DiffUtil.ItemCallback<StoryUI>() {
        override fun areItemsTheSame(oldItem: StoryUI, newItem: StoryUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoryUI, newItem: StoryUI): Boolean {
            return oldItem == newItem
        }
    }
}
