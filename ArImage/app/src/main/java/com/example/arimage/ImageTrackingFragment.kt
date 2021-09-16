package com.example.arimage

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment

class ImageTrackingFragment : ArFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        planeDiscoveryController.apply {
            hide()
            setInstructionView(null)
        }
        arSceneView.planeRenderer.isEnabled = false
        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        setupAugmentedImageDatabase(config, session)
        return config
    }

    private fun setupAugmentedImageDatabase(
        config: Config,
        session: Session?
    ) {
        val augmentedImageDb = AugmentedImageDatabase(session)
        val assetManager = requireContext().assets
        listOf("chantarelle", "fly_agaric").forEach {
            val inputStream = assetManager.open("img/$it.jpg")
            val augmentedImageBitmap = BitmapFactory.decodeStream(inputStream)
            augmentedImageDb.addImage(it, augmentedImageBitmap)
        }
        config.augmentedImageDatabase = augmentedImageDb
    }

}