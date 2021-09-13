package com.example.audiolab.data.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.util.Log
import com.example.audiolab.data.file.`interface`.FileProperties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.time.LocalTime

class AudioPlayer(
    override val fileName: String,
    override val fileDir: String
) : FileProperties {
    var currentTrack: AudioTrack? = null
        private set


    fun playAudio(
        iStream: InputStream
    ): String {
        buildTrack()

        val startTime = LocalTime.now().toString()
        val minBufferSize = currentTrack!!.bufferSizeInFrames
        currentTrack!!.setVolume(0.2f)
        currentTrack!!.play()

        var i = 0

        val buffer = ByteArray(minBufferSize)


        try {
            println("PLAY_START")
            i = iStream.read(buffer, 0, minBufferSize)
            while (i != -1) {
                println("PLAY")
                currentTrack!!.write(buffer, 0, i)
                i = iStream.read(buffer, 0, minBufferSize)
            }
        } catch (e: IOException) {
            Log.e("FYI", "Stream read error $e")
        }

        try {
            iStream.close()
            println("PLAY_STOP")
        } catch (e: IOException) {
            Log.e("FYI", "Close error $e")
        }

        currentTrack!!.stop()
        currentTrack!!.release()



        return startTime
    }

    private fun buildTrack() {
        val minBufferSize = AudioTrack.getMinBufferSize(
            44100, AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        val aBuilder = AudioTrack.Builder()
        val aAttr: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        val aFormat: AudioFormat = AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(44100)
            .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
            .build()
        currentTrack = aBuilder.setAudioAttributes(aAttr)
            .setAudioFormat(aFormat)
            .setBufferSizeInBytes(minBufferSize)
            .build()
    }
}