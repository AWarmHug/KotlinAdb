package script.test

class Adb(val name: String, val cmd: String) {

    fun runAndWaitExec(): TestResult {

        println(this.toString())
        val current = System.currentTimeMillis()
        val exec = Runtime.getRuntime().exec(cmd)
        exec.waitFor()

        val time = System.currentTimeMillis() - current
        println("耗时 = $time")

        val result = String(exec.inputStream.readAllBytes())
        println("result = $result")

        val error = String(exec.errorStream.readAllBytes())
        println(error)

        if (error.isNotEmpty()) {
            return TestResult(false, error, time)
        } else {
            return TestResult(true, result, time)
        }
    }


    override fun toString(): String {
        return "Adb(name='$name', cmd='$cmd')"
    }
}