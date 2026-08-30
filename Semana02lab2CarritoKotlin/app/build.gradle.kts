plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.vasquez.lab2carritokotlin"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.vasquez.lab2carritokotlin"
        minSdk = 24
        targetSdk = 37
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}

// Tarea personalizada para ejecutar tu archivo Carrito.kt en la consola
tasks.register<JavaExec>("runCarrito") {
    group = "application"
    mainClass.set("com.vasquez.lab2carritokotlin.CarritoKt")
    classpath = configurations.getByName("debugRuntimeClasspath") +
            files(layout.buildDirectory.dir("intermediates/javac/debug/compileDebugJavaWithJavac/classes"),
                layout.buildDirectory.dir("tmp/kotlin-classes/debug"))
}