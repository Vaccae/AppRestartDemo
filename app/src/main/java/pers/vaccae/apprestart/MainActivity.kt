package pers.vaccae.apprestart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : BaseCompatActivity() {

    val btn: Button by lazy { findViewById(R.id.btn) }
    val tvmsg:TextView by lazy { findViewById(R.id.tv_msg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            tvmsg.text = "打开Test"
            var activity = ActivityStack.currentActivity()
            Log.i("test", activity.toString())
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }

}