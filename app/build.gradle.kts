plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.kotlin_7pract"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kotlin_7pract"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    //UI tests dependencies
    // Основные зависимости Espresso
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.6.1")

    // Зависимости для тестирования действий (например, клики, ввод текста)
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.6.1")

    // Зависимости для тестирования Intents (например, проверка отправки интентов)
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.6.1")

    // Зависимости для тестирования веб-видов (WebView)
    androidTestImplementation ("androidx.test.espresso:espresso-web:3.6.1")

    // Зависимости для тестирования правил (например, ActivityScenarioRule)
    androidTestImplementation ("androidx.test:rules:1.6.1")

    // Зависимости для запуска тестов
    androidTestImplementation ("androidx.test:runner:1.6.2")

    // Зависимости для JUnit4
    androidTestImplementation ("androidx.test.ext:junit:1.2.1")

    //Unit tests dependencies
    testImplementation(libs.robolectric.robolectric)
    testImplementation("io.mockk:mockk:1.13.12")
    implementation(libs.testng)
    testImplementation("org.mockito:mockito-core:5.13.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation(libs.junit)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.okhttp)
    implementation(libs.glide)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.sdkcoroutines)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}