package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.ui.fragment.KEY_SELECTED_POSITION
import com.example.retrofit.ui.fragment.PresidentDetailFragment
import com.example.retrofit.ui.fragment.PresidentListFragment
import com.example.retrofit.ui.viewmodel.PresidentViewModel
import com.example.retrofit.utils.Injector

class MainActivity : AppCompatActivity(), PresidentClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PresidentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vmFactory = Injector.providePresidentViewModelFactory()
        viewModel =
            ViewModelProvider(this, vmFactory).get(PresidentViewModel::class.java)


        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PresidentListFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }

    /**
     * Implement a click event handler for the president list items in the activity to handle
     * the event outside of the adapter.
     */
    override fun onPress(position: Int) {
        // Pass the clicked president position to the next fragment
        viewModel.selectPresident(position)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<PresidentDetailFragment>(R.id.fragmentContainerView)
            addToBackStack(null)
        }
    }

}