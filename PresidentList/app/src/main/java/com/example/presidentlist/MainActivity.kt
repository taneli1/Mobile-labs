package com.example.presidentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.presidentlist.databinding.ActivityMainBinding
import com.example.presidentlist.fragment.KEY_SELECTED_POSITION
import com.example.presidentlist.fragment.PresidentDetailFragment
import com.example.presidentlist.fragment.PresidentListFragment

class MainActivity : AppCompatActivity(), PresidentClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val bundle = bundleOf(KEY_SELECTED_POSITION to position)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<PresidentDetailFragment>(R.id.fragmentContainerView, args = bundle)
            addToBackStack(null)
        }
    }

}