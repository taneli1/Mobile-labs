package com.example.retrofit.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit.ui.viewmodel.PresidentViewModel
import com.example.retrofit.databinding.FragmentPresidentDetailBinding

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

        observers()
        return binding.root
    }

    private fun bindPresident(tempBinding: FragmentPresidentDetailBinding) {
        tempBinding.president = viewModel.selectedPresident.value
        tempBinding.executePendingBindings()
    }

    private fun observers() {
        viewModel.queryResponse.observe(viewLifecycleOwner) {
            binding.hitcount.text = it.query.searchinfo.totalhits.toString()
        }
    }

}