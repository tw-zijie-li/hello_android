import java.util.Properties
import java.io.FileInputStream


plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

// Create a variable called keystorePropertiesFile, and initialize it to your
// keystore.properties file, in the rootProject folder.
val keystorePropertiesFile = rootProject.file("keystore.properties")

// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Load your keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    signingConfigs {
        getByName("debug") {
            keyAlias = keystoreProperties["debug-keyAlias"] as String
            keyPassword = keystoreProperties["debug-keyPassword"] as String
            storeFile = file(keystoreProperties["debug-storeFile"] as String)
            storePassword = keystoreProperties["debug-storePassword"] as String
        }
        create("release") {
            keyAlias = keystoreProperties["release-keyAlias"] as String
            keyPassword = keystoreProperties["release-keyPassword"] as String
            storeFile = file(keystoreProperties["release-storeFile"] as String)
            storePassword = keystoreProperties["release-storePassword"] as String
        }
    }
    namespace = "com.thoughtworks.androidtrain"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.thoughtworks.androidtrain"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}