package com.chm.m.ui.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

/**
 * @Desc : tab接口
 * @Author: chenhongmou
 * @Time: 2022/4/25 16:27
 */
public interface IMTab<D> extends IMTabLayout.OnTabSelectedListener<D>{

    void setMTabInfo(@NonNull D data);

    //动态修改某个item的大小
    void resetHeight(@Px int height);

}
