plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("androidx.navigation.safeargs.kotlin")
    
    kotlin("plugin.serialization") version "1.9.24"

}

android {
    namespace = "com.example.prueba4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.prueba4"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Latex
    implementation("ru.noties:jlatexmath-android:0.2.0")
    implementation("ru.noties:jlatexmath-android-font-cyrillic:0.2.0")
    implementation("ru.noties:jlatexmath-android-font-greek:0.2.0")

    // Compose
    implementation("androidx.compose.runtime:runtime-android:1.6.8")
    implementation("androidx.compose.foundation:foundation-android:1.6.8")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.compose.material:material:1.6.8")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.3.0-beta05")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))

    // Supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.3"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.6.0")
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.6.0")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.6.0")
    implementation("io.ktor:ktor-client-android:2.3.12")

    implementation ("androidx.activity:activity:1.9.1")

    // Authentication Supabase
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("io.ktor:ktor-client-cio:2.3.12")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

//    // Google Fonts
//    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.8")
//
//    // Jetpack Navigation
//    implementation("androidx.navigation:navigation-compose:2.8.0-rc01")
//    implementation("androidx.navigation:navigation-fragment:2.8.0-rc01")
//    implementation("androidx.navigation:navigation-ui:2.8.0-rc01")
//    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.8.0-rc01")
//    androidTestImplementation("androidx.navigation:navigation-testing:2.8.0-rc01")
//
//    // AndroidView en Compose
//    implementation("androidx.compose.ui:ui-tooling")
//
//    // JSON
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
//    implementation("com.google.code.gson:gson:2.10.1")

    implementation(kotlin("script-runtime"))

}