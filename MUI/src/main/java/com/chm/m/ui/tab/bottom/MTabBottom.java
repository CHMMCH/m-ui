package com.chm.m.ui.tab.bottom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chm.m.ui.R;
import com.chm.m.ui.tab.common.IMTab;

/**
 * @Desc :
 * @Author: chenhongmou
 * @Time: 2022/4/25 17:28
 */
public class MTabBottom extends RelativeLayout implements IMTab<MTabBottomInfo<?>> {

    private MTabBottomInfo<?> tabInfo;
    private ImageView tabImageView;
    private TextView tabIconView;
    private TextView tabNameView;

    public MTabBottom(Context context) {
        this(context,null);
    }

    public MTabBottom(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MTabBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.m_tab_bottom,this);
    }


    @Override
    public void setMTabInfo(@NonNull MTabBottomInfo<?> data) {

    }

    @Override
    public void resetHeight(int height) {

    }

    @Override
    public void onTabSelectedChange(int index, @Nullable MTabBottomInfo<?> prevInfo, @NonNull MTabBottomInfo<?> nextInfo) {

    }
}
