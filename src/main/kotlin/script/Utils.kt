package script

import java.nio.file.Paths

fun String.getUsefulInfoByKeyword(startWord: String, endWord:String="\n"): String {
    val start = indexOf(startWord) + startWord.length

    val end = indexOf(endWord)

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
