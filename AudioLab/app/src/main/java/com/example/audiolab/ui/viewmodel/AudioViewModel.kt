package com.example.audiolab.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.audiolab.data.audio.AudioPlayer
import com.example.audiolab.data.audio.SimpleAudioRecorder
import kotlinx.coroutines.*

class AudioViewModel(
    private val audioRecorder: SimpleAudioRecorder,
    private val audioPlayer: AudioPlayer
) : ViewModel() {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    val recording = audioRecorder.isRecording

    fun startRecording() {
        scope.launch {
            audioRecorder.startRecording()
        }
    }

    fun stopRecording() {
        scope.launch {
            audioRecorder.stopRecording()
        }
    }

    fun playRecording() {
        scope.launch {
            withContext(Dispatchers.IO) {
                val input = audioRecorder.openInputStream()
                input?.let {
                    audioPlayer.playAudio(it)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class AudioViewModelFactory(
        private val audioRecorder: SimpleAudioRecorder, private val audioPlayer: AudioPlayer
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AudioViewModel(audioRecorder, audioPlayer) as T
        }
    }
}