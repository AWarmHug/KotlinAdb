package script.test

import main.kotlin.script.*
import script.getUsefulInfoByKeyword

class TotalTestAction : BaseTestAction() {
    override fun testAction() {
        val result = Adb("获取包名", getCurrentPackage()).runAndWaitExec()
        if (result.isSuccess) {
            val pkg = result.resultStr.getUsefulInfoByKeyword("u0 ", "/")
            Adb("杀进程", stopApp(pkg)).runAndWaitExec()
            Adb("冷启动", coldStart(pkg)).runAndWaitExec()
            Adb("monkey", monkey(pkg)).runAndWaitExec()
            Adb("获取Fps", fps(pkg)).runAndWaitExec()
        }

    }
}