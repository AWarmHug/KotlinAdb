package main.kotlin.script

import script.getPropertyOsName
import script.test.DumpTestAction
import script.test.FpsTestAction
import script.test.StartTestAction
import script.test.TotalTestAction

fun main() {
    val coldStart = TotalTestAction()
    coldStart.testAction()
//    println(getPropertyOsName())
}


main()