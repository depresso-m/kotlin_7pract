package com.example.kotlin_7pract

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_7pract.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var urlInput: EditText
    private lateinit var loadButton: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        urlInput = binding.urlInput
        loadButton = binding.loadButton
        imageView = binding.imageView

        loadButton.setOnClickListener {
            if (urlInput.text.toString() != "") {
                val url = urlInput.text.toString()
                loadAndSaveImage(url)
            }
            else
            {
                Toast.makeText(this, "Введите url", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadAndSaveImage(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            // Network thread
            val imageBitmap = withContext(Dispatchers.IO) {
                loadImageFromNetwork(url)
            }

            // Disk thread
            withContext(Dispatchers.IO) {
                saveImageToDisk(imageBitmap)
            }

            // Update UI on the main thread
            withContext(Dispatchers.Main) {
                imageView.setImageBitmap(imageBitmap)
            }
        }
    }

    private fun loadImageFromNetwork(url: String): Bitmap? {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        return try {
            val response = client.newCall(request).execute()
            val inputStream: InputStream = response.body!!.byteStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveImageToDisk(bitmap: Bitmap?) {
        if (bitmap != null) {
            val file = File(filesDir, "downloaded_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        }
    }
}
