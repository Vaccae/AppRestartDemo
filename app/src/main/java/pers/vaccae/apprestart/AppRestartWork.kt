package pers.vaccae.apprestart

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 09:25
 * 功能模块说明：
 */
class AppRestartWork(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private var TAG = "restart"
    private var KeyStr = "ReStartTime"

    override fun doWork(): Result {
        //检测是否可以重启
        if (isInReStartTime()) {
            if (isInTimeScope(2, 0, 5, 0)) {
                Log.i(TAG, "Success")
                val ReStartTime = setNextReStartTime(1)
                SpHelper.putString(BaseApp.mContext!!, KeyStr, ReStartTime)
                BaseApp.reStartApp()
            }
            else {
                val ReStartTime = setNextReStartTime()
                SpHelper.putString(BaseApp.mContext!!, KeyStr, ReStartTime)
            }
        }
        return Result.success()
    }

    /**指定下次启动时间
     *
     * stype 类型：1-明天的2点开始， 0-15分钟后重新开启  2-当天的2点开始
     */
    fun setNextReStartTime(stype: Int = 0): String {
        val today = Date()
        val cal = Calendar.getInstance()
        cal.time = today;
        when (stype) {
            0 -> {
                cal.add(Calendar.MINUTE, 15)
            }
            1 -> {
                cal.set(Calendar.HOUR_OF_DAY, 2)
                cal.set(Calendar.MINUTE, 0)
                cal.add(Calendar.DAY_OF_MONTH, 1)
            }
            else -> {
                cal.set(Calendar.HOUR_OF_DAY, 2)
                cal.set(Calendar.MINUTE, 0)
            }
        }
        val f = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val res = f.format(cal.time)
        Log.i(TAG, "Calendar : ${f.format(cal.time)}")
        return res
    }


    //判断是否大于本次重启时间
    fun isInReStartTime(): Boolean {
        var res = false
        val f = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //获取上次重启时间
        val ReStartTimeStr =
            SpHelper.getString(BaseApp.mContext!!, KeyStr, "")
        Log.i(TAG, "ReStartTime:$ReStartTimeStr")
        if (ReStartTimeStr!!.equals("")) {
            val setReStartTime = setNextReStartTime(2)
            SpHelper.putString(BaseApp.mContext!!, KeyStr, setReStartTime)
            return false
        }

        val ReStartTime = f.parse(ReStartTimeStr).time
        Log.i(TAG, "ReStartTime:$ReStartTimeStr")
        //获取当前时间
        val nowTime = System.currentTimeMillis()
        Log.i(TAG, "nowTime:${f.format(nowTime)}")
        return nowTime > ReStartTime
    }


    /**
     * 判断当前系统时间是否在指定时间的范围内
     *
     * sHour 开始小时,例如22
     * sMin  开始小时的分钟数,例如30
     * eHour   结束小时,例如 8
     * eMin    结束小时的分钟数,例如0
     * true表示在范围内, 否则false
     */
    fun isInTimeScope(sHour: Int, sMin: Int, eHour: Int, eMin: Int): Boolean {
        var res = false

        val cal = Calendar.getInstance()
        val nowHour = cal.get(Calendar.HOUR_OF_DAY)
        val nowMin = cal.get(Calendar.MINUTE)
        //获取当前时间
        val nowTime = nowHour * 60 + nowMin
        Log.i(TAG, "nowTime:$nowTime")
        //起始时间
        val sTime = sHour * 60 + sMin
        Log.i(TAG, "sTime:$sTime")
        //结束时间
        val eTime = eHour * 60 + eMin
        Log.i(TAG, "eTime:$eTime")

        res = nowTime in sTime..eTime
        return res;
    }
}