plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id ("org.jetbrains.kotlin.plugin.serialization")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.vaishnavi.nine"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vaishnavi.nine"
        minSdk = 26
        targetSdk = 34
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation(project(mapOf("path" to ":model")))
    implementation(project(mapOf("path" to ":repository")))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("io.insert-koin:koin-android:latest.release")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:latest.release")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:latest.release")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:latest.release")
    implementation ("androidx.compose.ui:ui:latest.release")

    implementation ("androidx.compose.ui:ui:latest.release")
            implementation ("androidx.activity:activity-compose:latest.release")
            implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:latest.release")
            implementation ("androidx.compose.runtime:runtime-livedata:latest.release")
            implementation ("androidx.compose.runtime:runtime-rxjava2:latest.release")
    implementation ("androidx.compose.ui:ui-tooling:latest.release")
    implementation ("androidx.compose.foundation:foundation:latest.release")
    implementation ("androidx.compose.material:material:latest.release")
    implementation ("androidx.compose.material:material-icons-core:latest.release")
    implementation ("androidx.compose.material:material-icons-extended:latest.release")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:latest.release")

    implementation("io.coil-kt:coil-compose:latest.release")
    implementation ("org.mockito.kotlin:mockito-kotlin:latest.release")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:latest.release")
    testImplementation ("org.mockito:mockito-inline:latest.release")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.coil-kt:coil-base:latest.release")
    implementation("io.coil-kt:coil-compose:latest.release")
    implementation ("com.google.accompanist:accompanist-webview:latest.release")
    //implementation ("androidx.navigation:navigation-safe-args-gradle-plugin:latest.release")



    debugImplementation ("androidx.fragment:fragment-testing:1.7.0-alpha06")
    debugImplementation("androidx.fragment:fragment-ktx:1.7.0-alpha06")
    debugImplementation("androidx.test:core:1.5.0")
    debugImplementation("androidx.test:rules:1.5.0")
    debugImplementation("androidx.test:runner:1.5.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.0.0-beta05")

}