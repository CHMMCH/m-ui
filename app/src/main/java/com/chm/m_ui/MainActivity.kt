package com.chm.m_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chm.m.library.log.*
import com.chm.m.ui.tab.bottom.MTabBottom
import com.chm.m.ui.tab.bottom.MTabBottomInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MLogManager.init(object : MLogConfig(){
            override fun includeTread(): Boolean {
                return true
            }
        },MConsolePrinter())
        MLog.a("test")

        val tabBottom = findViewById<MTabBottom>(R.id.mtb_main)
        val homeInfo = MTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "ffd44949"
        )
        tabBottom.setMTabInfo(homeInfo)

    }
}