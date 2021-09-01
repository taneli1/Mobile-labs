package com.example.cameralab

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File


class CameraManager {

    /**
     * Must be called unconditionally, in the initialization path of an activity.
     * Register an activity to handle ActivityResult, with a function to run when a bitmap
     * is received here.
     *
     * @return A function, which starts the camera intent when called.
     */
    fun setupCamera(
        activity: AppCompatActivity,
        resultHandler: (bitMap: Bitmap) -> Unit
    ): () -> Unit {

        // Create a temp path for the file
        val imgPath = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile("temp_img", ".jpg", imgPath)
        val photoURI: Uri = FileProvider.getUriForFile(
            activity,
            "com.example.cameralab.fileprovider",
            imageFile
        )

        val pictureAction =
            activity.registerForActivityResult(ActivityResultContracts.TakePicture()) {
                if (it) {
                    val imageBitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                    resultHandler(imageBitmap)
                }
            }

        // Use the URI to save the picture to files
        return { pictureAction.launch(photoURI) }
    }
}