// app/build.gradle.kts
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.proyecto"
    compileSdk = 34 // o el que uses

    defaultConfig {
        applicationId = "com.example.proyecto"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    // Alinea targets Java/Kotlin para evitar el error anterior
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

kotlin {
    jvmToolchain(21)
}


dependencies {

    // Lifecycle ViewModel con viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    // (opcional pero común si usas coroutines en Android)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    // MPAndroidChart (recuerda: con 'v' y ya añadiste JitPack en settings.gradle.kts)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Room (ejemplo)
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Kotlin stdlib (opcional con plugin 2.0)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")

    // Otros (appcompat/material/etc) según tu proyecto
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.12.0")
}
