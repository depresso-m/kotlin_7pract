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

//Robolectric используется для запуска Android-специфичных тестов в JVM

//Этот тест проверяет следующие аспекты:

//Обработка ошибок при загрузке изображения: Убеждается, что при попытке загрузить изображение по некорректному URL возвращается null.

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class ImageLoadingErrorTest {

    @Test
    fun testLoadImageFromNetworkError() {
        val invalidUrl = "https://invalid-url.com/image.png"
        val bitmap = loadImageFromNetwork(invalidUrl)
        assertNull(bitmap)
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
}