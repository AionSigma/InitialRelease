package com.aionsigma.android.View.Register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.aionsigma.android.R
import kotlinx.android.synthetic.main.activity_register.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val MY_REQUEST_CAMERA = 10
    val MY_REQUEST_WRITE_CAMERA = 11
    val CAPTURE_CAMERA = 12

    val MY_REQUEST_READ_GALLERY = 13
    val MY_REQUEST_WRITE_GALLERY = 14
    val MY_REQUEST_GALLERY = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun imageButtonLocalOnClicked(view: View){

    }

    fun imageButtonCameraOnClicked(view: View){
        captureImage()
    }

    private fun checkPermissionCamera(): Boolean{
        val permissionCheck = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@RegisterActivity, arrayOf<String>(Manifest.permission.CAMERA),MY_REQUEST_CAMERA)
            return false
        }
        else{
            return true
        }
    }

    private fun checkPermissionCameraWrite() :Boolean{
        val permissionCheck = ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@RegisterActivity, arrayOf<String>(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE),MY_REQUEST_WRITE_CAMERA)
            return false
        }
        else{
            return true
        }
    }

    private fun captureImage(){
        if( checkPermissionCameraWrite() && checkPermissionCamera()){
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, CAPTURE_CAMERA)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CAPTURE_CAMERA && resultCode == RESULT_OK ){
            if(data != null){
                val extras = data.getExtras()
                try {
                    val imageBitmap = extras.get("data") as Bitmap
                    val path = saveImage(imageBitmap)
                    Toast.makeText(this, "Image Saved!$path", Toast.LENGTH_SHORT).show()
                    profile_image!!.setImageBitmap(imageBitmap)

                }
                catch (e: IOException){
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/aionsigmaIMG"
    }
}
