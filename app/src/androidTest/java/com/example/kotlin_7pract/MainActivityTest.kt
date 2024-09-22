package com.example.kotlin_7pract


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    //Проверяет, что EditText имеет подсказку (hint) "Enter image URL".
    @Test
    fun testUrlInputHint() {
        onView(withId(R.id.urlInput))
            .check(matches(withHint("Enter image URL")))
    }

    //Проверяет, что Button loadButton имеет текст "Load Image".
    @Test
    fun testLoadButtonText() {
        onView(withId(R.id.loadButton))
            .check(matches(withText("Load Image")))
    }

    //Проверяет, что Button отображается и находится по центру экрана.
    @Test
    fun testLoadButtonCentered() {
        onView(withId(R.id.loadButton))
            .check(matches(isCompletelyDisplayed()))
            .check(matches(isDisplayed()))
    }

    //Проверяет, что ImageView отображается после того, как пользователь вводит URL и нажимает кнопку "Load Image".
    @Test
    fun testImageViewDisplayedAfterButtonClick() {
        onView(withId(R.id.urlInput)).perform(typeText("https://avatars.yandex.net/get-music-content/5503671/3f9ca429.a.27682710-1/m1000x1000?webp=false"))
        onView(withId(R.id.loadButton)).perform(click())

        // Добавляем задержку, чтобы убедиться, что изображение загружено
        TimeUnit.SECONDS.sleep(3)

        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }

    //Проверяет, что ImageView не отображается, если пользователь нажимает кнопку "Load Image" без ввода URL.
    @Test
    fun testImageViewNotDisplayedWithoutUrl() {
        onView(withId(R.id.loadButton)).perform(click())
        onView(withId(R.id.imageView)).check(matches(not(isDisplayed())))
    }
}