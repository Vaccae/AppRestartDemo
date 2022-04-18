package pers.vaccae.apprestart

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class TestActivity : BaseCompatActivity() {

    val btn: Button by lazy { findViewById(R.id.btn) }
    val tvmsg: TextView by lazy { findViewById(R.id.tv_msg) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btn.setOnClickListener {
            tvmsg.text = "重启APP"

            var activity = ActivityStack.currentActivity()
            Log.i("test", activity.toString())
            BaseApp.reStartApp()
        }
    }

}