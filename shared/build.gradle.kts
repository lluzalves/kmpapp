import com.android.build.gradle.internal.ide.kmp.KotlinAndroidSourceSetMarker.Companion.android
import com.google.devtools.ksp.gradle.KspTaskMetadata

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp.kmp)
    alias(libs.plugins.sqlitedelightPlugin)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
                allWarningsAsErrors = false
                freeCompilerArgs += arrayOf("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }
    }
    sqldelight {
        databases {
            create("KmpAppDatabase") {
                packageName.set("com.example.kmp.kmpapp.database")
            }
            linkSqlite = true
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
                kotlin.srcDir("build/generated/ksp/metadata/androidMain/kotlin")
                implementation(libs.koin.annotations)
                api(libs.ktor.client.okhttp)
                api(libs.sqldelight.db.android)
            }
        }

        val commonMain by getting {
            dependencies {
                kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
                implementation(libs.koin)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.components.resources)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.material)
                implementation(compose.components.uiToolingPreview)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(libs.koin.annotations)
                implementation(libs.koin.core)
                implementation(libs.koin)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.logging)
                implementation(libs.kotlin.serialization)
                implementation(libs.ktor.json)
                implementation(libs.ktor.content.negotiation)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.coil.core)
                implementation(libs.kamel.compose)
                implementation(libs.coil.network)
                implementation(libs.kmp.viewmodel)
                implementation(libs.voyager.navigation)
                implementation(libs.voyager.tabbar)
                implementation(libs.voyager.transitions)
                implementation(libs.sqldelight.db.coroutines)
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
                kotlin.srcDir("build/generated/ksp/metadata/iosMain/kotlin")
                implementation(libs.koin.annotations)
                implementation(libs.ktor.client.darwin)
                api(libs.sqldelight.db.ios)
            }
        }
    }

    kotlin.sourceSets.commonMain {
        tasks.withType<KspTaskMetadata> {
            kotlin.srcDir(
                destinationDirectory
            )
        }
    }
    sourceSets["commonMain"].kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    sourceSets["androidMain"].kotlin.srcDir("build/generated/ksp/android/androidMain/kotlin")
    sourceSets["iosMain"].kotlin.srcDir("build/generated/ksp/android/iosMain/kotlin")

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
