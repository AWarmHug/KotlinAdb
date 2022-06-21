package script

typealias GetUserfulInfo<T> = (String) -> T

class Adb<T>(val name: String, val cmd: String, private val block: (GetUserfulInfo<T>)) {

    fun runAndWaitExec(): AdbResult<T> {

        println(this.toString())
        val current = System.currentTimeMillis()
        val exec = Runtime.getRuntime().exec(cmd)
        exec.waitFor()

        val time = System.currentTimeMillis() - current
        println("耗时 = $time")

        val result = String(exec.inputStream.readAllBytes())
        println("result = $result")

        val newResult = block.invoke(result)

        val error = String(exec.errorStream.readAllBytes())
        println(error)

        if (error.isNotEmpty()) {
            return AdbResult<T>(Error(error), time)
        } else {

            return AdbResult<T>(newResult, time)
        }
    }

    override fun toString(): String {
        return "Adb(name='$name', cmd='$cmd', block=$block)"
    }


}
