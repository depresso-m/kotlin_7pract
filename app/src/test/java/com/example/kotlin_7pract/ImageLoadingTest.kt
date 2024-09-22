package com.example.kotlin_7pract

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.InputStream

//Robolectric используется для запуска Android-специфичных тестов в JVM

//Этот тест проверяет следующие аспекты:
//
//Загрузка изображения по URL: Убеждается, что изображение может быть загружено по указанному URL.
//
//Декодирование изображения: Проверяет, что загруженные данные могут быть успешно декодированы в объект Bitmap.
//
//Обработка ошибок: Убеждается, что исключения, возникающие во время загрузки или декодирования, корректно обрабатываются.

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class ImageLoadingTest {

    @Test
    fun testLoadImageFromNetwork() {
        val url = "https://avatars.yandex.net/get-music-content/5503671/3f9ca429.a.27682710-1/m1000x1000?webp=false"
        val bitmap = loadImageFromNetwork(url)
        assertNotNull(bitmap)
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