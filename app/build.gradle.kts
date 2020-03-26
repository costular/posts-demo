import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}


android {
    compileSdkVersion(App.compileVersion)
    buildToolsVersion(App.buildToolsVersion)
    defaultConfig {
        applicationId = "com.costular.postsdemo"
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            buildConfigField("String", Config.API_BASE, "\"http://jsonplaceholder.typicode.com/\"")
        }
        getByName("debug") {
            // This way we could set a different url depending on the variant or flavor
            buildConfigField("String", Config.API_BASE, "\"http://jsonplaceholder.typicode.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin)

    implementation(Libs.appCompat)
    implementation(Libs.ktx)
    implementation(Libs.lifecycle)
    implementation(Libs.constraintLayout)
    implementation(Libs.material)
    implementation(Libs.navigation)
    implementation(Libs.navigationUi)
    implementation(Libs.koinViewModel)
    implementation(Libs.koinFragment)
    implementation(Libs.rxJava)
    implementation(Libs.rxKotlin)
    implementation(Libs.rxAndroid)
    implementation(Libs.room)
    kapt(Libs.roomCompiler)
    implementation(Libs.roomRxJava)
    implementation(Libs.moshi)
    kapt(Libs.moshiCodeGenCompiler)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)
    implementation(Libs.retrofitRx)
    implementation(Libs.glide)
    kapt(Libs.glideCompiler)
    implementation(Libs.lottie)

    testImplementation(Libs.junit)
    testImplementation(Libs.koinTest)
    testImplementation(Libs.mockk)
    testImplementation(Libs.truth)
    testImplementation(Libs.coreTesting)

    debugImplementation(Libs.fragmentTest)

    androidTestImplementation(Libs.junitAndroid)
    androidTestImplementation(Libs.espresso)
    androidTestImplementation(Libs.kakao)
    androidTestImplementation(Libs.navigationTesting)
    androidTestImplementation(Libs.coreTesting)
    androidTestImplementation(Libs.truth)
    androidTestImplementation(Libs.koinTest)
}