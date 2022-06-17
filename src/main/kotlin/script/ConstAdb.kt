package main.kotlin.script

import java.io.File

val APP_PACKAGE_NAME = "com.bertadata.qxb"

val APP_START_COLD = "adb shell am start -W ${APP_PACKAGE_NAME}/com.bertadata.qxb.module.main.splash.SplashActivity"


fun getInstallCmd(apkFile: File): String {
    return "adb install -r -d -t $apkFile"
}