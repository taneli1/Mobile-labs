package com.example.internalsensor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.internalsensor.databinding.FragmentProximitySensorBinding

class ProximitySensorFragment : Fragment() {
    private lateinit var binding: FragmentProximitySensorBinding
    private lateinit var sensorListener: ProximitySensorListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProximitySensorBinding.inflate(inflater)

        sensorListener = ProximitySensorListener(requireActivity(), lifecycleScope)

        observers()
        return binding.root
    }

    private fun observers() {
        sensorListener.distanceInCm.observe(viewLifecycleOwner) {
            binding.txt.text = "Distance from sensor: ${it}"
            val color = resources.getColor(if (!it.equals(0f)) R.color.main else R.color.black)
            binding.img.drawable.setTint(color)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorListener.registerListener()
    }

    override fun onPause() {
        super.onPause()
        sensorListener.unregisterListener()
    }

}