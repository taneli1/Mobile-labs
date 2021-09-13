package com.example.audiolab.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.audiolab.R
import com.example.audiolab.databinding.FragmentRecordAudioBinding
import com.example.audiolab.ui.viewmodel.AudioViewModel
import com.example.audiolab.utils.Injector
import com.example.audiolab.utils.Values.FILENAME


class RecordAudioFragment : Fragment() {
    private var _binding: FragmentRecordAudioBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: AudioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordAudioBinding.inflate(inflater)

        // Checked in MainAct
        val dir = requireActivity().getExternalFilesDir(null)

        viewModel = ViewModelProvider(
            this,
            Injector.provideAudioViewModelFactory(FILENAME, dir!!.absolutePath)
        ).get(AudioViewModel::class.java)

        setup()
        return binding.root
    }

    private fun setup() {
        binding.btnRecord.setOnClickListener {
            viewModel.startRecording()
        }

        binding.btnStopRecord.setOnClickListener {
            viewModel.stopRecording()
        }

        binding.btnReplay.setOnClickListener {
            viewModel.playRecording()
        }

        viewModel.recording.observe(viewLifecycleOwner) {
            binding.txtRecordingState.text =
                if (it) getString(R.string.is_recording) else getString(R.string.not_recording)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}