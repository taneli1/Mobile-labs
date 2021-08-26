package com.example.presidentlist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presidentlist.utils.Injector
import com.example.presidentlist.PresidentClickListener
import com.example.presidentlist.databinding.FragmentPresidentListBinding
import com.example.presidentlist.viewmodel.PresidentViewModel

const val KEY_SELECTED_POSITION = "position"

class PresidentListFragment : Fragment() {

    private lateinit var binding: FragmentPresidentListBinding
    private lateinit var viewModel: PresidentViewModel
    private lateinit var adapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this.requireActivity()).get(PresidentViewModel::class.java)
        adapter = Injector.providePresidentListAdapter(viewModel, requireContext() as PresidentClickListener)
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