package script.adb

import script.getApkPath
import script.getCurrentPackage
import script.install
import script.pullApk
import script.Adb
import script.getProjectPath
import script.getUsefulInfoByKeyword
import java.io.File


fun GetCurrentPackage(): Adb<String> = Adb("获取包名", getCurrentPackage()) {
    return@Adb it.getUsefulInfoByKeyword("u0 ", "/")
}

data class ApkPath(val pkgName: String, val apkPath: String)


fun GetApkPath(pkgName: String): Adb<ApkPath> = Adb("获取路径", getApkPath(pkgName)) {
    return@Adb ApkPath(pkgName, it.getUsefulInfoByKeyword("package:"))
}


data class PullApkInfo(val pkgName: String, val apkPath: String, val outPath: String)

fun PullApk(pkgName: String, apkPath: String, outPath: String = "${getProjectPath()}/apk/$pkgName"): Adb<PullApkInfo> {

    val file = File(outPath)
    if (!file.exists()) {
        file.mkdirs()
    }

    val adb = Adb<PullApkInfo>("拷贝Apk", pullApk(apkPath, outPath)) {
        return@Adb PullApkInfo(pkgName, apkPath, outPath)
    }
    return adb
}

data class InstallApkInfo(val isSuccess: Boolean)

fun InstallApk(apkPath: String): Adb<InstallApkInfo> {
    val adb = Adb<InstallApkInfo>("安装Apk", install(apkPath)) {
        return@Adb InstallApkInfo(it.contains("Success"))
    }
    return adb
}



