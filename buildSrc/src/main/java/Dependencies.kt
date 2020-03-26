
object Config {
    val API_BASE = "API_BASE"
}

object App {

    val compileVersion = 29
    val buildToolsVersion = "29.0.2"
    val minSdk = 21
    val targetSdk = 29
    val versionCode = 1
    val versionName = "0.1"

}

object Libs {

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val material = "com.google.android.material:material:${Versions.material}"
    val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
    val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    val moshiCodeGenCompiler = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    val junit = "junit:junit:${Versions.junit}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val fragmentTest = "androidx.fragment:fragment-testing:${Versions.fragment}"

    val junitAndroid = "androidx.test.ext:junit:1.1.1"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigation}"
    val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"
}

object Versions {

    val kotlin = "1.3.50"
    val koin = "2.1.1"
    val rxJava = "2.2.19"
    val rxKotlin = "2.4.0"
    val rxAndroid = "2.1.1"

    val room = "2.2.5"
    val material = "1.2.0-alpha05"
    val appCompat = "1.1.0"
    val ktx = "1.2.0"
    val constraintLayout = "1.1.3"
    val lifecycle = "2.2.0"
    val fragment = "1.3.0-alpha01"
    val navigation = "2.3.0-alpha02"
    val mockk = "1.9.3"
    val junit = "4.12"
    val truth = "1.0.1"
    val moshi = "1.9.2"
    val coreTesting = "2.1.0"
    val retrofit = "2.8.0"
    val kakao = "2.3.0"
    val espresso = "3.2.0"

}