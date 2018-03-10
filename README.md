# Complex RecyclerView in Kotlin.

[![Twitter](https://img.shields.io/badge/Twitter-@saveendhiman-blue.svg?style=flat)](https://twitter.com/saveendhiman)

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)


Hi guys, I have made demo application for ComplexRecyclerView in Kotlin. This is the open project for any contributer who want to improve something here.

This Sample show list of users along with their items with pagination and many other things.
offset : Position where you need to start your users.
limit: number of users you want to fetch in single hit.

1. User items images are square.
2. Horizontal and vertical spacings between every image are equal.
3. If items are even, then display them 2 in each row.
4. If items are odd, display the first one to span the full row, and show the remaining spanning 2 in each row.
5. Implemented pagination.

It usage of following libraries:

* [Retrofit2] for REST API.

* [RX java] for background process and Retrofit integration.

* [Dagger2] for dependency injection.

* [Glide] for image loading.

* [Fabric] for crashlytics.

* [Timber] for logging.

It uses MVP (Model View Presenter) pattern. MVP is a derivative from the well known MVC (Model View Controller), which for a while now is gaining importance in the development of Android applications.This project also contains basic utility classes required for day to day programming.

Utils classes.


# Here is what the app gradle look likes.

    apply plugin: 'com.android.application'
    apply plugin: 'kotlin-android'
    apply plugin: 'kotlin-android-extensions'
    apply plugin: 'kotlin-kapt'

    android {
    compileSdkVersion rootProject.ext.compileSdkVersion
    buildToolsVersion rootProject.ext.buildToolsVersion

    //app versioning
    def versionMajor = 1
    def versionMinor = 0
    def versionPatch = 0
    def versionBuild = 0

    defaultConfig {
        applicationId "com.complexrecycler"
        minSdkVersion rootProject.ext.minSdkVersion
        vectorDrawables.useSupportLibrary = true
        targetSdkVersion rootProject.ext.targetSdkVersion
        versionCode versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
        versionName "${versionMajor}.${versionMinor}.${versionPatch}"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            debuggable false
        }
        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets {
        main.java.srcDirs += 'src/main/kotlin'
    }

    }

    dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    implementation "com.android.support:appcompat-v7:$rootProject.ext.supportLibraryVersion"
    implementation "com.android.support.constraint:constraint-layout:$rootProject.ext.constraintVersion"
    testImplementation "junit:junit:$rootProject.ext.junitVersion"
    androidTestImplementation "com.android.support.test:runner:$rootProject.ext.testrunnerVersion"
    androidTestImplementation "com.android.support.test.espresso:espresso-core:$rootProject.ext.espressoVersion"
    implementation "com.android.support:design:$rootProject.ext.supportLibraryVersion"
    implementation "com.android.support:cardview-v7:$rootProject.ext.supportLibraryVersion"

    kapt "com.google.dagger:dagger-compiler:$daggerVersion"
    compile "com.google.dagger:dagger:$daggerVersion"

    compile "io.reactivex.rxjava2:rxandroid:$rootProject.ext.rxAndroidVersion"
    compile "io.reactivex.rxjava2:rxjava:$rootProject.ext.rxJavaVersion"

    compile "com.squareup.retrofit2:retrofit:$rootProject.ext.retrofitVersion"
    compile "com.squareup.retrofit2:converter-gson:$rootProject.ext.retrofitVersion"
    compile "com.squareup.okhttp3:logging-interceptor:$rootProject.ext.loggerVersion"
    compile "com.squareup.okhttp3:okhttp:$rootProject.ext.okhttpVersion"
    compile "com.squareup.retrofit2:adapter-rxjava2:$rootProject.ext.retrofitVersion"

    compile "com.github.d-max:spots-dialog:$rootProject.ext.dialogProgressVersion@aar"
    compile "com.android.support:recyclerview-v7:$rootProject.ext.supportLibraryVersion"
    compile "com.github.bumptech.glide:glide:$rootProject.ext.glideVersion"

    compile "com.jakewharton.timber:timber:$rootProject.ext.timberVersion"

     }


#Start from

minSdkVersion 16

#LICENSE

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

#Authors

[Saveen Dhiman] original Author


[Saveen Dhiman]:        https://github.com/saveendhiman

[Retrofit2]: 		https://square.github.io/retrofit
[RX java]:		https://github.com/ReactiveX/RxJava
[Dagger2]: 		https://google.github.io/dagger
[Glide]:              https://github.com/bumptech/glide
[Fabric]:               https://get.fabric.io/#
[Timber]:               https://github.com/JakeWharton/timber


