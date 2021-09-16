package com.example.arimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var arFragment: ArFragment
    private var viewRenderable: ViewRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: HERE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        Log.d(TAG, "setup: 1")
        arFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as ArFragment
        Log.d(TAG, "setup: 2")
        ViewRenderable.builder()
            .setView(this, R.layout.view_renderable_text)
            .build()
            .thenAccept { viewRenderable = it }
        Log.d(TAG, "setup: 3")
        arFragment.arSceneView.scene.addOnUpdateListener { frameUpdate() }
    }

    private fun frameUpdate() {
        val arFrame = arFragment.arSceneView.arFrame
        if (arFrame == null || arFrame.camera.trackingState != TrackingState.TRACKING) {
            return
        }

        val updatedAugmentedImages = arFrame.getUpdatedTrackables(AugmentedImage::class.java)
            .forEach {
                when (it.trackingState) {
                    null -> return@forEach
                    TrackingState.PAUSED -> {
                        // Image was detected, but not enough data to estimate its location
                        val text =
                            resources.getString(R.string.txt_image_found_missing_info) + it.name
                        Toast.makeText(this, text, Toast.LENGTH_SHORT)
                            .show()

                    }
                    TrackingState.STOPPED -> {
                        val text = resources.getString(R.string.txt_track_stop) + it.name
                        Toast.makeText(this, text, Toast.LENGTH_SHORT)
                            .show()
                    }
                    TrackingState.TRACKING -> {
                        val anchors = it.anchors
                        if (anchors.isEmpty()) {
                            findViewById<ImageView>(R.id.fit_image).visibility = View.GONE

                            val pose = it.centerPose
                            val anchor = it.createAnchor(pose)
                            val anchorNode = AnchorNode(anchor)

                            anchorNode.setParent(arFragment.arSceneView.scene)

                            val imgNode = TransformableNode(arFragment.transformationSystem).apply {
                                setParent(anchorNode)
                                viewRenderable?.view?.findViewById<TextView>(R.id.txt_img)?.text =
                                    it.name
                                renderable = viewRenderable
                            }
                        }
                    }
                    else -> println("Else?")
                }
            }

    }

}