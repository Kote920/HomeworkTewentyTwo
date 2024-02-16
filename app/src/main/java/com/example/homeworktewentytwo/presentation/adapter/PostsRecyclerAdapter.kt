package com.example.homeworktewentytwo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktewentytwo.R
import com.example.homeworktewentytwo.databinding.PostItemRecyclerBinding
import com.example.homeworktewentytwo.databinding.StoryItemRecyclerBinding
import com.example.homeworktewentytwo.presentation.extensions.loadImage
import com.example.homeworktewentytwo.presentation.model.PostUI
import com.example.homeworktewentytwo.presentation.model.StoryUI

class PostsRecyclerAdapter(private val detailsClick: (PostUI) -> Unit) :
    ListAdapter<PostUI, PostsRecyclerAdapter.TripsViewHolder>(PostsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TripsViewHolder(
        PostItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {
        holder.bind()
    }

    inner class TripsViewHolder(private val binding: PostItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: PostUI


        fun bind() {
            model = currentList[adapterPosition]
            val picturesList = model.images
            picturesList?.let {
                val picturesAdapter = PicturesRecyclerAdapter(it)
                binding.picturesRecyclerView.adapter = picturesAdapter
            }
            binding.apply {
                val iv = model.owner.profile
                if(iv != null){
                    ivAvatar.loadImage(iv)
                }else{
                    ivAvatar.setImageResource(R.drawable.ic_error)
                }

                picturesRecyclerView.layoutManager = GridLayoutManager(root.context, 3)
                tvTitle.text = model.title
                tvCommentsNumber.text = model.comments.toString()
                tvLikesNumber.text = model.likes.toString()
                tvFirstName.text = model.owner.firstName
                tvLastName.text = model.owner.lastName
                tvDate.text = model.owner.postDate

            }
            listeners()
        }

        private fun listeners() {
            binding.btnDetails.setOnClickListener {
                detailsClick.invoke(model)
            }
        }

    }

    class PostsDiffUtil : DiffUtil.ItemCallback<PostUI>() {
        override fun areItemsTheSame(oldItem: PostUI, newItem: PostUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostUI, newItem: PostUI): Boolean {
            return oldItem == newItem
        }
    }
}
