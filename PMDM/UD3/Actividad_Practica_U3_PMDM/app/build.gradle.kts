plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") // Procesador moderno (KSP), opcional


}

android {
    namespace = "com.kodeleku.project_gestor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kodeleku.project_gestor"
        minSdk = 31
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
    viewBinding{
        enable=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // DATASTORE
    implementation (libs.androidx.datastore.preferences)

    // ROOM
    implementation (libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx) // Extensiones para Coroutines
    ksp(libs.androidx.room.compiler) // Alternativa moderna "Kotlin"
    //kapt("androidx.room:room-compiler:2.6.1") // Procesador de anotaciones "kotlin"
    //annotationProcessor("androidx.room:room-compiler:2.6.1") // Procesador "Java"

    // LIFECYCLE
    implementation (libs.androidx.lifecycle.runtime.ktx)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}