package com.example.presidentlist.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presidentlist.viewmodel.PresidentViewModel
import com.example.presidentlist.R
import com.example.presidentlist.databinding.FragmentPresidentDetailBinding

class PresidentDetailFragment : Fragment() {
    private lateinit var binding: FragmentPresidentDetailBinding
    private lateinit var viewModel: PresidentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this.requireActivity()).get(PresidentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresidentDetailBinding.inflate(inflater)

        bindPresident(binding)

        return binding.root
    }

    private fun bindPresident(tempBinding: FragmentPresidentDetailBinding) {
        // Get the pos for the president to display from bundle
        val clickedPosition = requireArguments().getInt(KEY_SELECTED_POSITION)

        tempBinding.president = viewModel.getPresident(clickedPosition)
        tempBinding.executePendingBindings()
    }

}