package script.test

import main.kotlin.script.*
import main.kotlin.script.getApkPath
import script.getProjectPath
import script.getUsefulInfoByKeyword
import java.io.File

class InstallTestAction : BaseTestAction() {
    override fun testAction() {
        Adb("获取路径", getApkPath(APP_PACKAGE_NAME)).runAndWaitExec()
            .let {
                if (it.isSuccess) {
                    return@let it.resultStr.getUsefulInfoByKeyword("package:")
                } else {
                    throw Exception("报错")
                }
            }
            .let {
                val outPath = "${getProjectPath()}/apk/${APP_PACKAGE_NAME}"
                val file = File(outPath)
                if (!file.exists()) {
                    file.mkdirs()
                }
                return@let Adb("拷贝Apk", pullApk(it, outPath)).runAndWaitExec()
            }
            .let {
                if (it.isSuccess) {
                    return@let Adb(
                        "安装Apk",
                        install("${getProjectPath()}/apk/${APP_PACKAGE_NAME}/base.apk")
                    ).runAndWaitExec()
                } else {
                    throw Exception("安装Apk报错")
                }
            }.let {
                println(it)
            }

    }


}