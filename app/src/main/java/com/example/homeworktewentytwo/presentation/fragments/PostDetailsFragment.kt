package com.example.homeworktewentytwo.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homeworktewentytwo.R
import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.databinding.FragmentPostDetailsBinding
import com.example.homeworktewentytwo.presentation.base.BaseFragment
import com.example.homeworktewentytwo.presentation.event.DetailsEvent
import com.example.homeworktewentytwo.presentation.event.HomeEvent
import com.example.homeworktewentytwo.presentation.model.PostUI
import com.example.homeworktewentytwo.presentation.model.StoryUI
import com.example.homeworktewentytwo.presentation.state.DetailsState
import com.example.homeworktewentytwo.presentation.state.HomeState
import com.example.homeworktewentytwo.presentation.viewModel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding>(FragmentPostDetailsBinding::inflate) {

    private val viewModel: PostDetailsViewModel by viewModels()

    private val navArgs: PostDetailsFragmentArgs by navArgs()

    override fun setUp() {
        val bundle = arguments
        val value = bundle?.getString("key")
        if (value != null) {
            viewModel.onEvent(DetailsEvent.LoadDetails(value.toInt()))
        } else {
            viewModel.onEvent(DetailsEvent.LoadDetails(navArgs.id))
        }
        bindObserves()
    }

    override fun listeners() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                viewModel.postDetailsFlow.collect() {
                    manageDetailsResult(it)
                }


            }
        }

    }


    private fun manageDetailsResult(state: DetailsState) {
        state.errorMessage?.let {
            Log.d("ErrorLoadingData", it)
            viewModel.onEvent(DetailsEvent.ResetMessage)
        }

        manageLoader(state.isLoading, binding.pbDetails)

        state.isSuccess?.let {
            binding.tvTitle.text = it.title
            binding.tvDesc.text = it.shareContent
        }

    }

//
//    binding.tvTitle.text = post.title
//    binding.tvDesc.text = post.shareContent


}