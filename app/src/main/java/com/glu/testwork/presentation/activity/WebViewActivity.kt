package com.glu.testwork.presentation.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.glu.testwork.R
import com.glu.testwork.databinding.ActivityWebViewBinding
import com.glu.testwork.presentation.MyApp
import com.glu.testwork.presentation.WebViewSettings
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class WebViewActivity : AppCompatActivity() {
    lateinit var webView: WebView
    private var valueCallbackUri: ValueCallback<Uri>? = null
    private var valueCallbackArray: ValueCallback<Array<Uri>>? = null
    private var imBuild: String? = null
    private var campaign: String? = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.wbId)
        lifecycleScope.launch {
            startApsFlyer()
        }
        webViewSettings()
    }

    private fun startApsFlyer(){
        MyApp.liveDataAppsFlyer.observe(this) {
            it.forEach {
                when (it.key) {
                    "campaign" -> {
                        campaign = it.value.toString()
                        Log.d("test1", "$campaign")
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (valueCallbackUri == null && valueCallbackArray == null) {
            return
        }
        if (data?.data?.toString()?.startsWith("content://") == true) {
            valueCallbackUri?.onReceiveValue(data.data)
            valueCallbackArray?.onReceiveValue(arrayOf(Uri.parse(data.dataString)))
        } else {
            valueCallbackArray?.onReceiveValue(arrayOf(Uri.parse(imBuild)))
            valueCallbackArray = null
        }
    }

    private fun webViewSettings() {
        WebViewSettings().setSettings(webView)
        webView.webChromeClient = MyGoogleClient()
    }


    private fun cameraPerm() {
        EasyPermissions.requestPermissions(
            this,
            "From now on, camera permission is required.",
            0,
            Manifest.permission.CAMERA
        )
    }


    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    fun createImage(): File? {
        val imFormat: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fullImFormat = "JPEG_" + imFormat + "_"
        val myFile: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            fullImFormat,
            ".jpg",
            myFile
        )
    }

    inner class MyGoogleClient() : WebChromeClient() {
        override fun onShowFileChooser(
            weintId: WebView?, filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            valueCallbackArray = filePathCallback
            var imIntent: Intent?  = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (imIntent!!.resolveActivity(packageManager) != null) {
                var myFile: File? = null
                try {
                    myFile = createImage()
                    imIntent.putExtra("PhotoPath", imBuild)
                } catch (ex: IOException) {
                }
                if (myFile != null) {
                    imBuild = "file:" + myFile?.absolutePath
                    imIntent.putExtra(
                        MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(myFile)
                    )
                } else {
                    imIntent = null
                }
            }
            val mainIntent = Intent(Intent.ACTION_GET_CONTENT)
            mainIntent.addCategory(Intent.CATEGORY_OPENABLE)

            mainIntent.type = "image/*"
            val arIntent: Array<Intent?> =
                imIntent?.let { arrayOf(it) } ?: arrayOfNulls(0)
            val finishIntent = Intent(Intent.ACTION_CHOOSER)
            finishIntent.putExtra(Intent.EXTRA_INTENT, mainIntent)
            finishIntent.putExtra(Intent.EXTRA_TITLE, "Choose from...")
            finishIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arIntent)
            startActivityForResult(
                finishIntent,
                1234
            )
            cameraPerm()
            return true
        }

    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            return
        }
    }

}