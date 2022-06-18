package main.kotlin.script

import script.getPropertyOsName
import script.test.Adb
import script.test.TestResult
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


/**
 *
Status: ok
LaunchState: HOT
Activity: com.bingo.spadedemo/.ui.MainActivity
TotalTime: 144
WaitTime: 176
Complete
 *
 * ThisTime: 该Activity的启动耗时；

TotalTime: 应用自身启动耗时, ThisTime+应用application等资源启动时间；

WaitTime: 系统启动应用耗时, TotalTime+系统资源启动时间
 */
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

fun dump(pkg: String): String {
    return "adb shell dumpsys meminfo $pkg"
}

/**
 *
 * 打开手机：开发者选项—>profile GPU rendering —> in adb shell dumpsys gfxinfo
 *
 * Draw: 表示在Java中创建显示列表部分中，OnDraw()方法占用的时间。

Process：表示渲染引擎执行显示列表所花的时间，view越多，时间就越长。

Execute：表示把一帧数据发送到屏幕上排版显示实际花费的时间。

Draw + Process + Execute = 完整显示一帧 ，这个时间要小于16ms才能保存每秒60帧。
 */
fun fps(pkg: String): String {
    return "adb shell dumpsys gfxinfo $pkg"
}


fun monkey(pkg: String, times: Int = 1000): String {
    return "adb shell monkey -v -p $pkg $times"
}