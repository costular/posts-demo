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
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}")

    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.core:core-ktx:${Versions.ktx}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
    implementation("org.koin:koin-androidx-viewmodel:${Versions.koin}")
    implementation("org.koin:koin-androidx-fragment:${Versions.koin}")
    implementation("com.jakewharton.threetenabp:threetenabp:${Versions.threeTen}")
    implementation("io.reactivex.rxjava2:rxjava:${Versions.rxJava}")
    implementation("io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}")
    implementation("io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}")

    testImplementation("junit:junit:${Versions.junit}")
    testImplementation("org.koin:koin-test:${Versions.koin}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("org.threeten:threetenbp:1.3.2")

    debugImplementation("androidx.fragment:fragment-testing:${Versions.fragment}")

    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.navigation:navigation-testing:${Versions.navigation}")
}
