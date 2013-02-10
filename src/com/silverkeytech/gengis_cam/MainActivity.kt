package com.silverkeytech.gengis_cam

import android.app.Activity
import android.graphics.PixelFormat
import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.LinearLayout
import java.io.IOException
import android.view.ViewGroup.LayoutParams

public class MainActivity(): Activity() {
    private var surfaceView: SurfaceView? = null
    private var camera: Camera? = null
    var surfaceObject: SurfaceHolder.Callback? = null
    var surfaceHolder: SurfaceHolder? = null
    var surfaceHolderCallback: SurfaceHolder.Callback? = null

    protected override fun onCreate(savedInstanceState: Bundle?): Unit {
        getWindow()?.setFormat(PixelFormat.TRANSLUCENT)

        super.onCreate(savedInstanceState)
        surfaceView = SurfaceView(getApplicationContext())
        addContentView(surfaceView, LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        if (surfaceHolder == null){
            surfaceHolder = surfaceView?.getHolder()
            surfaceHolder?.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        }

        surfaceHolderCallback = surfaceCallback()
        surfaceHolder?.addCallback(surfaceHolderCallback)
    }

    fun surfaceCallback(): SurfaceHolder.Callback? {
        var callback = object : SurfaceHolder.Callback {
            public override fun surfaceDestroyed(holder: SurfaceHolder?): Unit {
                camera?.stopPreview()
                camera?.release()
                camera = null
            }

            public override fun surfaceCreated(holder: SurfaceHolder?): Unit {
                camera = Camera.open()
                try
                {
                    camera?.setPreviewDisplay(holder)
                }
                catch (exception: Exception) {
                    camera?.release()
                    camera = null
                }

            }
            public override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int): Unit {

                if (holder == null){
                    return
                }

                camera?.startPreview()
            }
        }
        return callback
    }
}
