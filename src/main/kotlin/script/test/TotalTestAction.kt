package script.test

import script.Adb
import script.getUsefulInfoByKeyword

class TotalTestAction : BaseTestAction() {
    override fun testAction() {
//        val result = GetCurrentPackageAction().runAndWaitExec()
//        if (result.isSuccess) {
//            val pkg = result.resultStr.getUsefulInfoByKeyword("u0 ", "/")
//            Adb("杀进程", stopApp(pkg)).runAndWaitExec()
//            Adb("冷启动", coldStart(pkg)).runAndWaitExec()
//            Adb("monkey", monkey(pkg)).runAndWaitExec()
//            Adb("获取Fps", fps(pkg)).runAndWaitExec()
//        }

    }
}