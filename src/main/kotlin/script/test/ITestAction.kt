package script.test

abstract class ITestAction {


    fun runAndWaitExec(cmd: String): TestResult {

        println(provideTestName())
        println("cmd = $cmd")
        val exec = Runtime.getRuntime().exec(cmd)
        exec.waitFor()

        val result = String(exec.inputStream.readAllBytes())

        println("result = $result")

        val error = String(exec.errorStream.readAllBytes())
        println(error)

        if (error.isNotEmpty()) {
            return TestResult(false, error)
        } else {
            return TestResult(true, result)
        }
    }

    abstract fun provideTestName(): String

    abstract fun provideTestCmd(): String

    fun testAction() {
        val cmd = provideTestCmd()

        runAndWaitExec(cmd)


    }


}