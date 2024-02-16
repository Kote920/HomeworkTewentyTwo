package com.example.homeworktewentytwo.presentation.fragments

import android.os.Bundle
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
            viewModel.getPostDetails(value.toInt())
        } else {
            viewModel.getPostDetails(navArgs.id)
        }
        bindObserves()
    }

    override fun listeners() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                viewModel.postDetailsFlow.collect() {
                    when (it) {

                        is Resource.Loading -> {
                            binding.pbDetails.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.pbDetails.visibility = View.GONE
                            val post = it.responseData
                            binding.tvTitle.text = post.title
                            binding.tvDesc.text = post.shareContent

                        }

                        is Resource.Failed -> {
                            binding.pbDetails.visibility = View.GONE
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