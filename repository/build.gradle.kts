plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
  // id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.vaishnavi.repository"
    compileSdk = 33

    defaultConfig {
        //applicationId = "com.vaishnavi.repository"
        minSdk = 24
        targetSdk = 33
//        versionCode = 1
//        versionName = "1.0"

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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.retrofit2:retrofit:latest.release")
    implementation("com.squareup.retrofit2:converter-gson:latest.release")
    implementation(project(":model"))
    implementation("com.squareup.okhttp3:logging-interceptor:latest.release")
    implementation("com.squareup.okhttp3:okhttp:latest.release")
    implementation ("io.insert-koin:koin-android:latest.release")
   // implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:latest.release")
   // implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:latest.release")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:latest.release")
    implementation ("androidx.core:core-ktx:latest.release")
    implementation ("org.mockito.kotlin:mockito-kotlin:latest.release")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:latest.release")
    testImplementation ("org.mockito:mockito-inline:latest.release")
}