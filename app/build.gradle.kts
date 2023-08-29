plugins {
    alias(libs.plugins.androidApp)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "mempool.space"
    compileSdk = 33

    defaultConfig {
        applicationId = "mempool.space"
        minSdk = 21
        targetSdk = 33
        compileSdkVersion = "android-33"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    kapt(libs.hiltCompiler)
    kapt(libs.moshiCompiler)

    implementation(projects.api)
    implementation(projects.biometric)

    implementation(libs.bundles.compose)
    implementation(libs.bundles.log)
    implementation(libs.bundles.network)

    implementation(libs.biometric)
    implementation(libs.coreKtx)
    implementation(libs.coroutine)
    implementation(libs.hiltAndroid)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.material)
    implementation(libs.navigation)
    implementation(libs.viewModelKtx)

    testImplementation(libs.bundles.unitTest)

    debugImplementation(libs.bundles.composeDebug)
}

configurations.testImplementation {
    exclude(module = "logback-android")
}
