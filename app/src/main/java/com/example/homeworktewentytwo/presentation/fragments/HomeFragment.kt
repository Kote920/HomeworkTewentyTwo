package com.example.homeworktewentytwo.presentation.fragments

import android.os.Bundle
import android.os.Message
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktewentytwo.R
import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.databinding.FragmentHomeBinding
import com.example.homeworktewentytwo.presentation.adapter.PostsRecyclerAdapter
import com.example.homeworktewentytwo.presentation.adapter.StoriesRecyclerAdapter
import com.example.homeworktewentytwo.presentation.base.BaseFragment
import com.example.homeworktewentytwo.presentation.event.HomeEvent
import com.example.homeworktewentytwo.presentation.model.PostUI
import com.example.homeworktewentytwo.presentation.model.StoryUI
import com.example.homeworktewentytwo.presentation.state.HomeState
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
        viewModel.onEvent(HomeEvent.LoadStories)
        viewModel.onEvent(HomeEvent.LoadPosts)
        bindPosts()
        bindStories()
    }

    override fun listeners() {


    }

    private fun initRecycler() {

        storiesAdapter = StoriesRecyclerAdapter()
        postsAdapter = PostsRecyclerAdapter {
            openPostDetails(it.id)
        }
        binding.apply {
            storiesRecyclerView.adapter = storiesAdapter
            storiesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            postsRecyclerView.adapter = postsAdapter
            postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun bindStories() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.storiesFlow.collect() {
                    manageStoryResult(it)
                }
            }
        }
    }

    private fun bindPosts() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postsFlow.collect() {
                    managePostResult(it)
                }
            }
        }
    }


    private fun openPostDetails(id: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment(
                id
            )
        )
    }


    private  fun managePostResult(state: HomeState<PostUI>) {
        state.errorMessage?.let {
            d("ErrorLoadingData", it)
            viewModel.onEvent(HomeEvent.ResetPostMessage)
        }

        manageLoader(state.isLoading, binding.pbHome)

        state.isSuccess?.let {
            postsAdapter.submitList(it)
        }

    }

    private  fun manageStoryResult(state: HomeState<StoryUI>) {
        state.errorMessage?.let {
            d("ErrorLoadingData", it)
            viewModel.onEvent(HomeEvent.ResetStoryMessage)
        }

        manageLoader(state.isLoading, binding.pbHome)

        state.isSuccess?.let {
            storiesAdapter.submitList(it)
        }

    }


}