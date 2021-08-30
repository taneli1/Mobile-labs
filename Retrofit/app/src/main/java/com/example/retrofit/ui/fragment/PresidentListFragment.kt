package com.example.retrofit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.utils.Injector
import com.example.retrofit.PresidentClickListener
import com.example.retrofit.databinding.FragmentPresidentListBinding
import com.example.retrofit.ui.viewmodel.PresidentViewModel

const val KEY_SELECTED_POSITION = "position"

class PresidentListFragment : Fragment() {

    private lateinit var binding: FragmentPresidentListBinding
    private lateinit var viewModel: PresidentViewModel
    private lateinit var adapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vmFactory = Injector.providePresidentViewModelFactory()
        viewModel =
            ViewModelProvider(this.requireActivity(), vmFactory).get(PresidentViewModel::class.java)

        adapter = Injector.providePresidentListAdapter(
            viewModel,
            requireContext() as PresidentClickListener
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresidentListBinding.inflate(inflater)
        setupRecyclerview()
        return binding.root
    }

    private fun setupRecyclerview() {
        binding.rvPresidents.layoutManager = LinearLayoutManager(context)
        binding.rvPresidents.adapter = adapter
    }
}