package com.example.homeworktewentytwo.presentation.fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktewentytwo.R
import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.databinding.FragmentHomeBinding
import com.example.homeworktewentytwo.presentation.adapter.PostsRecyclerAdapter
import com.example.homeworktewentytwo.presentation.adapter.StoriesRecyclerAdapter
import com.example.homeworktewentytwo.presentation.base.BaseFragment
import com.example.homeworktewentytwo.presentation.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var storiesAdapter: StoriesRecyclerAdapter
    private lateinit var postsAdapter: PostsRecyclerAdapter

    override fun setUp() {
        initRecycler()
        viewModel.getStoriesList()
        viewModel.getPostsList()
        bindPosts()
        bindStories()
    }

    override fun listeners() {

    }

    private fun initRecycler(){

        storiesAdapter = StoriesRecyclerAdapter()
        postsAdapter = PostsRecyclerAdapter()
        binding.apply {
            storiesRecyclerView.adapter = storiesAdapter
            storiesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            postsRecyclerView.adapter = postsAdapter
            postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

    }

     private fun bindStories() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                viewModel.storiesFlow.collect() {
                    when (it) {

                        is Resource.Loading -> {
                            binding.pbHome.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.pbHome.visibility = View.GONE
                            val storiesList = it.responseData
                            storiesAdapter.submitList(storiesList)

                        }

                        is Resource.Failed -> {
                            binding.pbHome.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }

                    }
                }


            }
        }
    }

    private fun bindPosts() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                viewModel.postsFlow.collect() {
                    when (it) {

                        is Resource.Loading -> {
                            binding.pbHome.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.pbHome.visibility = View.GONE
                            val postsList = it.responseData
                            postsAdapter.submitList(postsList)

                        }

                        is Resource.Failed -> {
                            binding.pbHome.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }

                    }
                }


            }
        }
    }

}