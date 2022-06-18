package script.test

import main.kotlin.script.dump
import main.kotlin.script.fps
import main.kotlin.script.getCurrentPackage
import script.getUsefulInfoByKeyword

class FpsTestAction : BaseTestAction() {

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
                return@let Adb("获取Fps", fps(it)).runAndWaitExec()
            }
            .let(::println)

    }
}