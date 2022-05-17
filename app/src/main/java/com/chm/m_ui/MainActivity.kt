package com.chm.m_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chm.m.library.log.*
import com.chm.m_ui.demo.tab.MTabBottomDemoActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MLog.a("test")

    }

    override fun onClick(v: View?) {
       when (v!!.id){
           R.id.bt_tab_bottom -> {
               startActivity(Intent(this,MTabBottomDemoActivity::class.java))
           }
       }
    }
}