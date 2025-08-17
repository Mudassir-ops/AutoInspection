plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.plugins)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.autoinspectionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.autoinspectionapp"
        minSdk = 24
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        dataBinding = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //dimens
    implementation(libs.dimens.sdp)
    implementation(libs.dimens.ssp)

    //dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    //lottie
    implementation(libs.lottie)
    //Glide
    implementation(libs.glide)
    //room database
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    // Retrofit
    implementation(libs.retrofit)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation("com.squareup.retrofit2:converter-gson:3.0.0")


}
kapt {
    correctErrorTypes = true
    useBuildCache = false
    generateStubs = true
}
