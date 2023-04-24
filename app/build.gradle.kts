import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

/**
 * https://github.com/gradle/gradle/issues/22797
 * Can update to Gradle 8 (and remove the suppress) and kotlin-android to 1.8.0 but Compose needs
 * 1.7.20 for now.
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.ataulm.pokenotes"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ataulm.pokenotes"
        minSdk = 28
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    constraints {
        implementation(libs.org.jetbrains.kotlin.stdlib.jdk7) {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation(libs.org.jetbrains.kotlin.stdlib.jdk8) {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    implementation(libs.activity.compose)
    implementation(libs.core.ktx)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.material3)
    implementation(libs.retrofit)

    implementation(libs.retrofit.moshi)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(platform(libs.compose.bom))
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bom))

    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
}
