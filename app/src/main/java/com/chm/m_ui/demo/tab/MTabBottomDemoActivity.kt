package com.chm.m_ui.demo.tab

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chm.m.library.util.MDisplayUtil
import com.chm.m.ui.tab.bottom.MTabBottomInfo
import com.chm.m.ui.tab.bottom.MTabBottomLayout
import com.chm.m_ui.R

class MTabBottomDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mtab_bottom_demo)
        initTabBottom()

    }

    private fun initTabBottom() {
        val mTabBottomLayout: MTabBottomLayout = findViewById(R.id.mtablayout)
        mTabBottomLayout.setTabAlpha(0.85f)
        val bottomInfoList: MutableList<MTabBottomInfo<*>> = ArrayList()

        val homeInfo = MTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "#ffd44949"
        )

        val chatInfo = MTabBottomInfo(
            "聊天",
            "fonts/iconfont.ttf",
            getString(R.string.if_chat),
            null,
            "#ff656667",
            "#ffd44949"
        )

//        val categoryInfo = MTabBottomInfo(
//            "分类",
//            "fonts/iconfont.ttf",
//            getString(R.string.if_category),
//            null,
//            "#ff656667",
//            "#ffd44949"
//        )

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.fire, null);
        val categoryInfo = MTabBottomInfo<String>(
            "分类",
            bitmap,
            bitmap
        )


        val recommendInfo = MTabBottomInfo(
            "推荐",
            "fonts/iconfont.ttf",
            getString(R.string.if_recommend),
            null,
            "#ff656667",
            "#ffd44949"
        )

        val cprofileInfo = MTabBottomInfo(
            "我的",
            "fonts/iconfont.ttf",
            getString(R.string.if_profile),
            null,
            "#ff656667",
            "#ffd44949"
        )

        bottomInfoList.add(homeInfo)
        bottomInfoList.add(chatInfo)
        bottomInfoList.add(categoryInfo)
        bottomInfoList.add(recommendInfo)
        bottomInfoList.add(cprofileInfo)
        mTabBottomLayout.inflateInfo(bottomInfoList)

        mTabBottomLayout.addTabSelectedChangeListener { _, _, nextinfo ->
            Toast.makeText(
                this,
                nextinfo.name,
                Toast.LENGTH_SHORT
            ).show()
        }
        mTabBottomLayout.defaultSelected(homeInfo)

        //修改单个Tab高度 实现凹凸效果
        val tabBottom = mTabBottomLayout.findTab(bottomInfoList[2])
        tabBottom?.apply { resetHeight(MDisplayUtil.dp2px(66f, resources)) }

    }
}