package com.example.audiolab.data.audio

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.lifecycle.MutableLiveData
import com.example.audiolab.data.file.`class`.ByteArrayHandler

class SimpleAudioRecorder(
    fileName: String,
    fileDir: String
) : ByteArrayHandler(fileName, fileDir) {
    private val tag = "SimpleAudioRecorder"

    private var recording = false

    var isRecording: MutableLiveData<Boolean> = MutableLiveData(false)
        private set
    private var recorder: AudioRecord? = null

    fun startRecording() {
        if (recording) {
            return
        }

        openOutputStream()
        buildRecorder()
        record()
    }

    fun stopRecording() {
        recording = false
        isRecording.postValue(false)
    }

    private fun record() {
        val minBufferSize = recorder!!.bufferSizeInFrames
        val audioData = ByteArray(minBufferSize)

        recorder?.startRecording().also {
            recording = true
            isRecording.postValue(true)
        }

        while (recording) {
            val byteCount = recorder?.read(audioData, 0, minBufferSize) ?: -1
            if (byteCount > 0) {
                writeToFileStream(audioData)
            }
        }

        recorder?.stop()
        closeOutputStream()
    }

    private fun buildRecorder() {
        val minBufferSize = AudioRecord.getMinBufferSize(
            44100,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        val aFormat = AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(44100)
            .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
            .build()

        recorder = AudioRecord.Builder()
            .setAudioSource(MediaRecorder.AudioSource.MIC)
            .setAudioFormat(aFormat)
            .setBufferSizeInBytes(minBufferSize)
            .build()
    }
}