package script.test

import main.kotlin.script.dump
import main.kotlin.script.getCurrentPackage
import script.getUsefulInfoByKeyword

class DumpTestAction : BaseTestAction() {

    override fun testAction() {
        Adb("获取包名", getCurrentPackage()).runAndWaitExec()
            .let {
                if (it.isSuccess) {
                    return@let it.resultStr.getUsefulInfoByKeyword("u0 ", "/")
                } else {
                    throw RuntimeException("获取包名报错")
                }
            }
            .let {
                return@let Adb("获取内存信息", dump(it)).runAndWaitExec()
            }
            .let(::println)

    }
}