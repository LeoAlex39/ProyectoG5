// build.gradle.kts (root)
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("com.android.library") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.0" apply false
}

// 👇 ¡Nada de kotlin("android") ni kotlin("kapt") aquí!
