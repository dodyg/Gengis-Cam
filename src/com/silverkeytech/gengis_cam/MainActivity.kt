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
import android.hardware.Camera.PictureCallback
import java.io.File
import android.util.Log
import android.os.Environment
import android.net.Uri
import java.text.SimpleDateFormat
import java.util.Date
import android.view.Menu
import android.view.MenuItem
import java.io.FileOutputStream

public class MainActivity(): Activity() {
    private var surfaceView: SurfaceView? = null
    private var camera: Camera? = null
    var surfaceObject: SurfaceHolder.Callback? = null
    var surfaceHolder: SurfaceHolder? = null
    var surfaceHolderCallback: SurfaceHolder.Callback? = null

    class object {
        val MEDIA_TYPE_IMAGE : Int = 1
        val MEDIA_TYPE_VIDEO : Int = 2
    }

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

    val pictureCallback : PictureCallback = object: PictureCallback{
        public override fun onPictureTaken(p0: ByteArray?, p1: Camera?) {
            var fl = this@MainActivity.getOutputMediaFile(MEDIA_TYPE_IMAGE)
            if (fl == null){
                return
            }

            try{
                val otp = FileOutputStream(fl!!)
                otp.write(p0!!)
                otp.close()
            }
            catch(e: Exception){

            }
        }
    }


    public override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = getMenuInflater()!!
        inflater.inflate(R.menu.main_menu, menu)

        return super<Activity>.onCreateOptionsMenu(menu)
    }

    public override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.getItemId()) {
            R.id.main_take_photo -> {
                camera?.takePicture(null, null, pictureCallback)
                return true
            }
            else -> {
                return false
            }
        }
    }


    protected override fun onPause() {
        super<Activity>.onPause()
        camera?.release()
        camera = null
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

    fun getOutputMediaFileUri(tipe : Int) : Uri{
        return Uri.fromFile(getOutputMediaFile(tipe))!!
    }

    /** Create a File for saving an image or video */
    fun getOutputMediaFile(tipe : Int) : File? {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "GengisCam")
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory")
                return null
            }
        }

        // Create a media file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var mediaFile : File? = null
        if (tipe == MEDIA_TYPE_IMAGE){
            mediaFile = File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg")
        } else if(tipe == MEDIA_TYPE_VIDEO) {
            mediaFile = File(mediaStorageDir.getPath() + File.separator +"VID_"+ timeStamp + ".mp4")
        }

        return mediaFile
    }
}
