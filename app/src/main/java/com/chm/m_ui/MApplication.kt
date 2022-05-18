package com.chm.m_ui

import android.app.Application
import com.chm.m.library.log.MConsolePrinter
import com.chm.m.library.log.MLogConfig
import com.chm.m.library.log.MLogManager

/**
 * @Desc :
 * @Author: chenhongmou
 * @Time: 2022/5/17 17:28
 */
class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MLogManager.init(object : MLogConfig() {

            override fun includeTread(): Boolean {
                return false
            }

            override fun stackTraceDepth(): Int {
                return 0
            }

        }, MConsolePrinter())
    }

}