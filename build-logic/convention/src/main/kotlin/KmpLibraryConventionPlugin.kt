import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import uz.dev.muhammadali.chirp.convertion.configureKotlinAndroid
import uz.dev.muhammadali.chirp.convertion.configureKotlinMultiPlatform
import uz.dev.muhammadali.chirp.convertion.libs
import uz.dev.muhammadali.chirp.convertion.pathToResourcePrefix

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            configureKotlinMultiPlatform()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                resourcePrefix = this@with.pathToResourcePrefix()
                experimentalProperties["android.experimental.kmp.EnableAndroidResources"] = "true"

            }

            dependencies {
                "commonMainImplementation"(libs.findLibrary("kotlinx-serialization-json").get())
                "commonTestImplementation"(libs.findLibrary("kotlin-test").get())
            }
        }
    }
}