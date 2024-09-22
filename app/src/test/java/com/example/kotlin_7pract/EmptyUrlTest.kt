package com.example.kotlin_7pract

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.InputStream

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class EmptyUrlTest {

    @Test
    fun testLoadImageFromEmptyUrl() {
        val emptyUrl = ""
        val bitmap = loadImageFromNetwork(emptyUrl)
        assertNull(bitmap)
    }

    private fun loadImageFromNetwork(url: String): Bitmap? {
        if (url.isEmpty()) {
            return null
        }

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
}