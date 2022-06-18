package script

import java.nio.file.Paths

fun String.getUsefulInfoByKeyword(keyword: String): String {
    val start = indexOf(keyword) + keyword.length

    val end = indexOf("\n")

    return substring(start, end)
}

fun getProjectPath(): String {
    return Paths.get("").toAbsolutePath().toString().removeSuffix("/src/main/kotlin/script")

}

//获取当前系统名称
fun getPropertyOsName(): String {
    val props = System.getProperties(); //获得系统属性集
    return props.getProperty("os.name");
}
