package com.example.kotlin_7pract

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File
import java.io.FileOutputStream

//Robolectric используется для запуска Android-специфичных тестов в JVM

//Этот тест проверяет следующие аспекты:
//
//Создание Bitmap из ресурсов: Убеждается, что изображение может быть загружено из ресурсов приложения.
//
//Сохранение изображения на диск: Проверяет, что изображение может быть успешно сохранено в указанной директории.
//
//Проверка существования файла: Убеждается, что файл был успешно создан и сохранен на диске.


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class ImageSavingTest {

    @Test
    fun testSaveImageToDisk() {
        // Загрузка тестового изображения из ресурсов
        val bitmap = BitmapFactory.decodeResource(RuntimeEnvironment.application.resources, R.drawable.test_image)
        saveImageToDisk(bitmap)
        val file = File(RuntimeEnvironment.application.filesDir, "downloaded_image.png")
        assertTrue(file.exists())
    }

    private fun saveImageToDisk(bitmap: Bitmap?) {
        if (bitmap != null) {
            val file = File(RuntimeEnvironment.application.filesDir, "downloaded_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        }
    }
}