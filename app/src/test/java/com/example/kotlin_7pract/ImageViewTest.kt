package com.example.kotlin_7pract

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

//Robolectric используется для запуска Android-специфичных тестов в JVM

//Этот тест проверяет следующие аспекты:

//Создание Bitmap из ресурсов: Убеждается, что изображение может быть загружено из ресурсов приложения.
//
//Установка Bitmap в ImageView: Проверяет, что метод setImageBitmap был вызван с правильным аргументом.

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class ImageViewTest {

    @Test
    fun testSetImageBitmap() {
        // Создание мока ImageView
        val imageView = mock(ImageView::class.java)

        // Загрузка тестового изображения из ресурсов
        val bitmap = BitmapFactory.decodeResource(RuntimeEnvironment.application.resources, com.example.kotlin_7pract.R.drawable.test_image)

        // Установка Bitmap в ImageView
        imageView.setImageBitmap(bitmap)

        // Проверка, что метод setImageBitmap был вызван с правильным аргументом
        verify(imageView).setImageBitmap(bitmap)
    }
}