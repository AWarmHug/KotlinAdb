package main.kotlin.script

import java.io.File



/**
 * 测试安装
 */
fun testInstall(){

    val apkPckPath="D:\\AndroidStudioProjects\\KotlinAdbScript\\apk"
    File(apkPckPath).listFiles().firstOrNull {
        runAndWaitExec(getInstallCmd(it))
    }

    runAndWaitExec(APP_START_COLD)
}


fun runAndWaitExec(cmd: String): Boolean {
    println(cmd)
    val exec = Runtime.getRuntime().exec(cmd)
    exec.waitFor()

    val result = String(exec.inputStream.readAllBytes())

    println("result = $result")

    val error = String(exec.errorStream.readAllBytes())
    println(error)
    return error.isEmpty()
}


testInstall()