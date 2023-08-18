plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.k4tr1n4.core_network"
    compileSdk = 33
    buildFeatures{
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        buildConfigField("String", "PRIV_API_KEY", "\"0cecfbecabcd1225e5ca27d18b141dfb764480fa\"")
        buildConfigField("String", "PUB_API_KEY", "\"13df12234d139baa6885622e6adada35\"")
        buildConfigField("String", "MARVEL_BASE_URL", "\"http://gateway.marvel.com/public/\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core-model")))
    testImplementation(project(mapOf("path" to ":core-test")))

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")

    //hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-compiler:2.47")

    //network
    implementation("com.github.skydoves:sandwich:1.3.7")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    //json parsing
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")

}