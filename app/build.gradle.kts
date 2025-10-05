plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.icare"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.icare"
        minSdk = 26
        targetSdk = 36
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

    // Defines the version for Room dependencies
    val room_version = "2.6.1"

    // Room runtime
    implementation("androidx.room:room-runtime:$room_version")

    // Room annotation processor (Java)
    annotationProcessor("androidx.room:room-compiler:$room_version")



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}