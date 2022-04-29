package com.chm.m.ui.tab.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @Desc : tabLayout接口
 * @Author: chenhongmou
 * @Time: 2022/4/25 16:20
 */
public interface IMTabLayout<Tab extends ViewGroup, D> {

    Tab findTab(@NonNull D data);

    void addTabSelectedChangeListener(OnTabSelectedListener<D> listener);

    void defaultSelected(@NonNull D defaultInfo);

    void inflateInfo(@NonNull List<D> infoList);

    //当底部tab被选中时返回 选中index 上一个选中的数据和下一个选中的数据
    interface OnTabSelectedListener<D>{
        void onTabSelectedChange(int index, @Nullable D prevInfo,@NonNull D nextInfo);
    }


}
