package script.test

import main.kotlin.script.APP_PACKAGE_NAME
import main.kotlin.script.back
import main.kotlin.script.coldStart
import main.kotlin.script.stopApp

class StartTestAction : BaseTestAction() {

    override fun testAction() {
        repeat(5) {
            Adb("冷启动", coldStart(APP_PACKAGE_NAME)).runAndWaitExec()
                .let {
                    return@let Adb("杀进程", stopApp(APP_PACKAGE_NAME)).runAndWaitExec()
                }
                .let {
                    println(it)
                }
        }

        repeat(5) {
            Adb("热启动", coldStart(APP_PACKAGE_NAME)).runAndWaitExec()
                .let {
                    return@let Adb("返回", back()).runAndWaitExec()
                }
                .let {
                    println(it)
                }
        }

    }
}