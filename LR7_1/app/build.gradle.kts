plugins {
    alias(libs.plugins.android.application)

    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.lr7_1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lr7_1"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // https://mvnrepository.com/artifact/com.google.android.gms/play-services-auth
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    // https://mvnrepository.com/artifact/androidx.sqlite/sqlite
    runtimeOnly("androidx.sqlite:sqlite:2.3.1")

    // https://mvnrepository.com/artifact/com.google.firebase/firebase-auth
    implementation("com.google.firebase:firebase-auth:22.3.1")


    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.2.0"))


    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")


    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries



}