package com.example.audiolab.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions

object PermissionUtils {

    fun permissionAudioRecord(activity: AppCompatActivity): Boolean {
        return checkPermissions(
            activity, Manifest.permission.RECORD_AUDIO
        )
    }

    private fun checkPermissions(activity: AppCompatActivity, permission: String): Boolean {
        if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            Log.d("DBG", "No audio recorder access")
            requestPermissions(activity, arrayOf(Manifest.permission.RECORD_AUDIO), 1);
            return true
        }
        return true
    }
}