package uz.dev.muhammadali.chirp.convertion

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiPlatform() {

    extensions.configure<LibraryExtension> {
        namespace = this@configureKotlinMultiPlatform.pathToPackageName()
    }
    configureAndroidTarget()

    extensions.configure<KotlinMultiplatformExtension> {
        listOf(
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = this@configureKotlinMultiPlatform.pathToFrameworkName()
            }
        }

        compilerOptions {
            freeCompilerArgs.add("-Xexpact-actual-classes")
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
        }
    }
}