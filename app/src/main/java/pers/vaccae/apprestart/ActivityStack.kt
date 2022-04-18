package pers.vaccae.apprestart

import android.app.Activity
import android.util.Log

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 11:34
 * 功能模块说明：
 */
object ActivityStack {
    private const val TAG = "ActivityStack"

    //当前活动的Activity列表
    private val activities: MutableList<Activity> = arrayListOf()

    //添加活动Activity
    @JvmStatic
    fun addActivity(activity: Activity?) {
        try {
            activity?.let {
                if (checkActivity(it)) {
                    removeActivity(it)
                }
                activities.add(it)
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString());
        }
    }

    //删除活动Activity
    @JvmStatic
    fun removeActivity(activity: Activity?) {
        try {
            activity?.let {
                activities.remove(it)
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString());
        }
    }

    //获取当前活动的Activity
    @JvmStatic
    fun currentActivity(): Activity? {
        var activity: Activity? = null
        if (activities.size > 0) {
            activity = activities[activities.size - 1]
        }
        return activity
    }

    //关闭当前活动的Activity
    @JvmStatic
    fun finishCurActivity() {
        val activity = currentActivity()
        activity?.let {
            it.finish()
            activities.remove(it)
        }
    }

    //关闭所有的Activity
    @JvmStatic
    fun finishAllActivity() {
        for (i in activities.size - 1 downTo 0) {
            val activity = activities[i]
            activity.finish()
            activities.removeAt(i)
        }
    }

    //检查Activity是否在列表中
    @JvmStatic
    private fun checkActivity(activity: Activity?): Boolean {
        var res = false
        activity?.let {
            res = activities.contains(activity)
        }
        return res
    }


}