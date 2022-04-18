package pers.vaccae.apprestart

import android.content.Context
import android.content.SharedPreferences

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 09:32
 * 功能模块说明：
 */
object SpHelper {
    private val spFileName = "app"

    fun getString(
        context: Context, strKey: String?,
        strDefault: String? = ""
    ): String? {
        val setPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        return setPreferences.getString(strKey, strDefault)
    }

    fun putString(context: Context, strKey: String?, strData: String?) {
        val activityPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        val editor = activityPreferences.edit()
        editor.putString(strKey, strData)
        editor.apply()
        editor.commit()
    }


    fun getBoolean(
        context: Context, strKey: String?,
        strDefault: Boolean? = false
    ): Boolean {
        val setPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        return setPreferences.getBoolean(strKey, strDefault!!)
    }

    fun putBoolean(
        context: Context, strKey: String?,
        strData: Boolean?
    ) {
        val activityPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        val editor = activityPreferences.edit()
        editor.putBoolean(strKey, strData!!)
        editor.apply()
        editor.commit()
    }


    fun getInt(context: Context, strKey: String?, strDefault: Int = -1): Int {
        val setPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        return setPreferences.getInt(strKey, strDefault)
    }

    fun putInt(context: Context, strKey: String?, strData: Int) {
        val activityPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        val editor = activityPreferences.edit()
        editor.putInt(strKey, strData)
        editor.apply()
        editor.commit()
    }


    fun getLong(context: Context, strKey: String?, strDefault: Long = -1): Long {
        val setPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        return setPreferences.getLong(strKey, strDefault)
    }

    fun putLong(context: Context, strKey: String?, strData: Long) {
        val activityPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        val editor = activityPreferences.edit()
        editor.putLong(strKey, strData)
        editor.apply()
        editor.commit()
    }

    fun getFloat(context: Context, strKey: String?, strDefault: Float = -1f): Float {
        val setPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        return setPreferences.getFloat(strKey, strDefault)
    }

    fun putFloat(context: Context, strKey: String?, strData: Float) {
        val activityPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        val editor = activityPreferences.edit()
        editor.putFloat(strKey, strData)
        editor.apply()
        editor.commit()
    }

    fun getStringSet(context: Context,strKey: String?): MutableSet<String>? {
        val setPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        return setPreferences.getStringSet(strKey, LinkedHashSet<String>())
    }

    fun putStringSet(context: Context, strKey: String?, strData: LinkedHashSet<String>?) {
        val activityPreferences: SharedPreferences = context.getSharedPreferences(
            spFileName, Context.MODE_PRIVATE
        )
        val editor = activityPreferences.edit()
        editor.putStringSet(strKey, strData)
        editor.apply()
        editor.commit()
    }
}