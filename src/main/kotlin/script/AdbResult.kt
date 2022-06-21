package script

class AdbResult<T> internal constructor(internal val result: Any?, val time: Long) {

    public val isSuccess: Boolean get() = result !is Error

    public val isFailure: Boolean get() = result is Error

    fun getOrNull(): T? =
        when {
            isFailure -> null
            else -> result as T
        }

    override fun toString(): String {
        return "AdbResult(isSuccess=$isSuccess, isFailure=$isFailure, result=$result, time=$time)"
    }


    public companion object {
        public fun <T> success(value: T, time: Long): AdbResult<T> = AdbResult(value, time)

        fun <T> failure(error: String, time: Long): AdbResult<T> = AdbResult(Error(error), time)

    }


}