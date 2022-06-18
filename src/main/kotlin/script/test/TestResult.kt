package script.test

data class TestResult(val isSuccess: Boolean, val resultStr: String, val time: Long) {

    override fun toString(): String {
        return "TestResult(isSuccess=$isSuccess, resultStr='$resultStr', time=$time)"
    }
}