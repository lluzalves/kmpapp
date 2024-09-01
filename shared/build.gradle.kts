import com.android.build.gradle.internal.ide.kmp.KotlinAndroidSourceSetMarker.Companion.android

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp.kmp)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.koin.annotations)
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.koin)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.components.resources)
                implementation(compose.foundation)
                implementation(compose.material3)
                api(libs.koin.annotations)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.koin.annotations)
            }
        }
    }

    sourceSets["commonMain"].kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    sourceSets["androidMain"].kotlin.srcDir("build/generated/ksp/android/androidMain/kotlin")

    dependencies {
        add("kspCommonMainMetadata", libs.koin.compiler)
        add("kspAndroid", libs.koin.compiler)
        add("kspIosX64", libs.koin.compiler)
        add("kspIosArm64", libs.koin.compiler)
        add("kspIosSimulatorArm64", libs.koin.compiler)

    }

    task("testClasses")

}

android {
    namespace = "com.daniel.kmpapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
