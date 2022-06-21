package script.test

import script.adb.GetApkPath
import script.adb.GetCurrentPackage
import script.adb.InstallApk
import script.adb.PullApk

class InstallTestAction : BaseTestAction() {
    override fun testAction() {

        GetCurrentPackage().runAndWaitExec().getOrNull()?.let {
            return@let GetApkPath(it).runAndWaitExec().getOrNull()
        }?.let {
            return@let PullApk(it.pkgName, it.apkPath).runAndWaitExec().getOrNull()
        }?.let {
            return@let InstallApk(it.apkPath).runAndWaitExec().getOrNull()
        }?.let {
            print(it.toString())
        }

    }


}