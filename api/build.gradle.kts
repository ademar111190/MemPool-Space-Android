plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
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

kapt {
    correctErrorTypes = true
}

dependencies {
    kapt(libs.hiltCompiler)
    kapt(libs.moshiCompiler)

    implementation(libs.bundles.log)
    implementation(libs.bundles.network)

    implementation(libs.coreKtx)
    implementation(libs.coroutine)
    implementation(libs.hiltAndroid)

    testImplementation(libs.bundles.unitTest)
}
