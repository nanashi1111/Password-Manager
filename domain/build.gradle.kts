plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  kotlin("kapt")
}

android {
  namespace = "com.passwordmanager.domain"
  compileSdk = 33

  defaultConfig {
    minSdk = 26

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.11.0")
  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-android-compiler:2.44")
  implementation(project(":data"))
  implementation(project(":data"))
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}