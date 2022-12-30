plugins {
    alias(libs.plugins.androidApp)
    alias(libs.plugins.kotlinAndroid)
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
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.api)
    implementation(libs.bundles.compose)
    implementation(libs.coreKtx)
    implementation(libs.lifecycleRuntimeKtx)
    testImplementation(libs.junit)
    debugImplementation(libs.bundles.composeDebug)
}
