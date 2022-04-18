package pers.vaccae.apprestart

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 10:10
 * 功能模块说明：
 */
class BaseApp : Application() {

    companion object {
        @JvmField
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null

        //重启APP
        @JvmStatic
        fun reStartApp(second: Int = 1) {
            mContext?.let { context->
                val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
                intent?.let {
                    //关闭所有Activity
                    ActivityStack.finishAllActivity()

                    it.putExtra("REBOOT", "reboot")
                    val restartintent = PendingIntent.getActivity(
                        context, 0, it, PendingIntent.FLAG_ONE_SHOT
                    )
                    val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + second * 1000, restartintent)
                    //android.os.Process.killProcess(android.os.Process.myPid())
                    System.exit(0)
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this

        //创建WorkManager任务
        val periodicwork =
            PeriodicWorkRequestBuilder<AppRestartWork>(5000, TimeUnit.MILLISECONDS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "AppReStart", ExistingPeriodicWorkPolicy.REPLACE,
            periodicwork
        )
    }

}