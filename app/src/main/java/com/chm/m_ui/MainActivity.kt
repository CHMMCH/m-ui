package com.chm.m_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chm.m.library.log.MLog
import com.chm.m.library.log.MLogConfig
import com.chm.m.library.log.MLogManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MLogManager.init(object : MLogConfig(){

        })

        MLog.a("ss")
    }
}