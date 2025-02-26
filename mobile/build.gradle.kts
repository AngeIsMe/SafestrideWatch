plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services") // Firebase services
}

android {
    namespace = "com.capstone.safestridew"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.capstone.safestridew"
        minSdk = 28
        targetSdk = 35
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
}

dependencies {

    // Core Android and Jetpack libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.drawerlayout)
    implementation(libs.androidx.gridlayout)

    // Firebase BOM for dependency management
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth) // Firebase Authentication
    implementation(libs.firebase.analytics) // Firebase Analytics

    // UI Libraries
    implementation(libs.cardview) // CardView
    implementation(libs.recyclerview) // RecyclerView
    implementation(libs.picasso) // Image loading
    implementation(libs.gson) // JSON parsing

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Reference the Wear app project
    wearApp(project(":wear"))
}
