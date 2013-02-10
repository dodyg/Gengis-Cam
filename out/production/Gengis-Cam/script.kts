// Module script for production
import kotlin.modules.*
fun project() {
    module("Gengis-Cam") {
        sources += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/src/com/silverkeytech/gengis_cam/MainActivity.kt"
        // Classpath
        classpath += "C:/Program Files (x86)/Android/android-sdk/platforms/android-16/android.jar"
        classpath += "C:/Program Files (x86)/Android/android-sdk/platforms/android-16/data/res"
        classpath += "C:/Program Files (x86)/Android/android-sdk/tools/support/annotations.jar"
        // Output directory, commented out
        //         classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/out/production/Gengis-Cam"
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/libs/kotlin-runtime.jar"
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/libs/android-support-v13.jar"
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/libs/gson-2.2.2.jar"
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/libs/ormlite-android-4.42.jar"
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/libs/ormlite-core-4.42.jar"
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/libs/simple-xml-2.6.7.jar"
        // Java Source Roots
        classpath += "C:/Users/Dody/Documents/GitHub/Gengis-Cam/src"
        classpath += "C:/Users/Dody/.IdeaIC12/system/compile-server/gengis-cam_5bf7c89e/android/generated_sources/Gengis-Cam/aapt"
        classpath += "C:/Users/Dody/.IdeaIC12/system/compile-server/gengis-cam_5bf7c89e/android/generated_sources/Gengis-Cam/build_config"
        // External annotations
        annotationsPath += "C:/Users/Dody/.IdeaIC12/config/plugins/Kotlin/kotlinc/lib/kotlin-jdk-annotations.jar"
    }
}
