plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "mempool.space.api"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.coreKtx)
    testImplementation(libs.junit)
}
