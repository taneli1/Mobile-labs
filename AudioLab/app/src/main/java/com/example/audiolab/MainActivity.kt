package com.example.audiolab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.audiolab.databinding.ActivityMainBinding
import com.example.audiolab.ui.fragment.RecordAudioFragment
import com.example.audiolab.ui.viewmodel.AudioViewModel
import com.example.audiolab.utils.Injector
import com.example.audiolab.utils.PermissionUtils
import com.example.audiolab.utils.Values.FILENAME
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AudioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PermissionUtils.permissionAudioRecord(this)

        initViewModel()

        // Dont do if no storage
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<RecordAudioFragment>(R.id.fragment_container)
            addToBackStack(null)
        }
    }

    private fun initViewModel() {
        if (Environment.getExternalStorageState() === Environment.MEDIA_MOUNTED) {
            val dir = this.getExternalFilesDir(null)
            println(dir!!.absolutePath)
            // Mkdir to ensure it exists when accessed later
            try {
                dir?.mkdirs()
            } catch (e: Exception) {
                Log.e("Error in mkdir", e.message.toString())
            }

            viewModel = ViewModelProvider(
                this,
                Injector.provideAudioViewModelFactory(FILENAME, dir!!.absolutePath)
            ).get(AudioViewModel::class.java)
        }
        // Return something, handle no mounted storage
    }
}