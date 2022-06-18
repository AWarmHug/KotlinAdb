package main.kotlin.script

import script.getPropertyOsName
import java.io.File

val APP_PACKAGE_NAME = "com.bingo.spadedemo"

val GET_PACKAGE_LIST = "adb shell pm list package"


//找出安装后的包名应用的apk所在位置----返回手机中apk的path
fun getApkPath(pkg: String): String = "adb shell pm path $pkg"

/**
 * 从手机导出apk到电脑
 * [apkPath] apk在手机上的目录，调用[getApkPath]所获取的，
 * [outPath] 为导出apk的目标路径
 */
fun pullApk(apkPath: String, outPath: String): String = "adb pull $apkPath $outPath"

fun getVersion(pkg: String): String = "adb shell dumpsys package $pkg | findstr versionCode"


//val APP_START_COLD = "adb shell am start -W ${APP_PACKAGE_NAME}/com.bertadata.qxb.module.main.splash.SplashActivity"


fun install(apkPath: String): String {
    return "adb install -r -d -t $apkPath"
}

fun coldStart(pkg: String): String {
    return "adb shell am start -W -n ${pkg}/.ui.MainActivity"
}

fun stopApp(pkg: String): String {
    return "adb shell am force-stop $pkg"
}

fun back(): String {
    return "adb shell input keyevent 3"
}

fun getCurrentPackage(): String {

    if (getPropertyOsName().contains("Mac")) {
        return "adb shell dumpsys window | grep mCurrentFocus"
    }

    return "adb shell dumpsys window | findstr mCurrentFocus"
}