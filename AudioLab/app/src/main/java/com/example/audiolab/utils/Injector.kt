package com.example.audiolab.utils

import com.example.audiolab.data.audio.AudioPlayer
import com.example.audiolab.data.audio.SimpleAudioRecorder
import com.example.audiolab.ui.viewmodel.AudioViewModel

object Injector {
    fun provideAudioViewModelFactory(
        fileName: String,
        dir: String
    ): AudioViewModel.AudioViewModelFactory {
        return AudioViewModel.AudioViewModelFactory(
            SimpleAudioRecorder(fileName, dir),
            AudioPlayer(fileName, dir)
        )
    }
}