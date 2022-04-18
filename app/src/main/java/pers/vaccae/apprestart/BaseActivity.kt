package pers.vaccae.apprestart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间： 12:52
 * 功能模块说明：
 */
open class BaseCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStack.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityStack.removeActivity(this)
    }
}