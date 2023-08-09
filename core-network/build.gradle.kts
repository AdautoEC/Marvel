plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
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

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

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

}