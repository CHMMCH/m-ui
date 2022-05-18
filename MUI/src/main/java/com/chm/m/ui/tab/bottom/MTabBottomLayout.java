package com.chm.m.ui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chm.m.library.util.MDisplayUtil;
import com.chm.m.library.util.MViewUtil;
import com.chm.m.ui.R;
import com.chm.m.ui.tab.common.IMTabLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Desc : MTabBottom容器控件
 * @Author: chenhongmou
 * @Time: 2022/5/17 15:16
 */
public class MTabBottomLayout extends FrameLayout implements IMTabLayout<MTabBottom, MTabBottomInfo<?>> {

    private static final String TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM";

    private List<OnTabSelectedListener<MTabBottomInfo<?>>> tabSelectedChangeListener = new ArrayList<>();
    //保存当前被选中的info
    private MTabBottomInfo<?> selectedInfo;
    private float bottomAlpha = 1f;
    //tabBottom高度
    private float tabBottomHeight = 56;
    //tabBottom的头部线条高度
    private float bottomLineHeight = 56;
    //tabBottom的头部线条颜色
    private String bottomLineColor = "#dfe0e1";

    private List<MTabBottomInfo<?>> infoList;

    public MTabBottomLayout(@NonNull Context context) {
        super(context);
    }

    public MTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public MTabBottom findTab(@NonNull MTabBottomInfo<?> info) {
        ViewGroup ll = findViewWithTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < getChildCount(); i++) {
            View child = ll.getChildAt(i);
            if (child instanceof MTabBottom) {
                MTabBottom tab = (MTabBottom) child;
                if (tab.getMTabInfo() == info) {
                    return tab;
                }
            }
        }
        return null;
    }

    @Override
    public void addTabSelectedChangeListener(OnTabSelectedListener<MTabBottomInfo<?>> listener) {
        tabSelectedChangeListener.add(listener);
    }

    @Override
    public void defaultSelected(@NonNull MTabBottomInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    @Override
    public void inflateInfo(@NonNull List<MTabBottomInfo<?>> infoList) {
        if (infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;
        //移除之前已经添加的view
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeViewAt(i);
        }
        selectedInfo = null;
        addBackground();

        //清除之前添加的MTabBottom listener  java foreach remove 问题
        Iterator<OnTabSelectedListener<MTabBottomInfo<?>>> iterator = tabSelectedChangeListener.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof MTabBottom) {
                iterator.remove();
            }
        }

        int height = MDisplayUtil.dp2px(tabBottomHeight, getResources());
        FrameLayout fl = new FrameLayout(getContext());
        fl.setTag(TAG_TAB_BOTTOM);
        //屏幕宽度/list个数 得出每一个list的平均宽度
        int width = MDisplayUtil.getDisplayWidthInPx(getContext()) / infoList.size();

        for (int i = 0; i < infoList.size(); i++) {
            MTabBottomInfo<?> info = infoList.get(i);
            LayoutParams params = new LayoutParams(width, height);
            //Tips:为何不用LinearLayout:当动态改变child大小后Gravity.Bottom会失效
            params.gravity = Gravity.BOTTOM;
            //左边距
            params.leftMargin = i * width;

            MTabBottom tabBottom = new MTabBottom(getContext());
            tabSelectedChangeListener.add(tabBottom);
            tabBottom.setMTabInfo(info);
            fl.addView(tabBottom, params);
            tabBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSelected(info);
                }
            });
        }
        LayoutParams flParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        flParams.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(fl, flParams);
        fixContentView();
    }

    public void setTabAlpha(float alpha) {
        this.bottomAlpha = alpha;
    }

    public void setTabHeight(float tabHeight) {
        this.tabBottomHeight = tabHeight;
    }

    public void setBottomLineHeight(float bottomLineHeight) {
        this.bottomLineHeight = bottomLineHeight;
    }

    public void setBottomLineColor(String bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
    }


    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));
        LayoutParams bottomLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, MDisplayUtil.dp2px(bottomLineHeight, getResources()));
        bottomLineParams.gravity = Gravity.BOTTOM;
        bottomLineParams.bottomMargin = MDisplayUtil.dp2px(tabBottomHeight - bottomLineHeight, getResources());
        addView(bottomLine, bottomLineParams);
        bottomLine.setAlpha(bottomAlpha);
    }


    private void onSelected(@NonNull MTabBottomInfo<?> nextInfo) {
        for (OnTabSelectedListener<MTabBottomInfo<?>> listener : tabSelectedChangeListener) {
            listener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        this.selectedInfo = nextInfo;
    }


    //添加背景色
    private void addBackground() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.m_bottom_layout_bg, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MDisplayUtil.dp2px(tabBottomHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        addView(view, params);
        view.setAlpha(bottomAlpha);
    }

    //修复内容区域的底部padding
    private void fixContentView() {
        if (!(getChildAt(0) instanceof ViewGroup)) {
            return;
        }
        ViewGroup rootView = (ViewGroup) getChildAt(0);

        //找出对应的滚动容器控件 在添加padding 防止导航栏挡住滚动内容
        ViewGroup targetView = MViewUtil.findTypeView(rootView, RecyclerView.class);
        if (targetView == null) {
            targetView = MViewUtil.findTypeView(rootView, ScrollView.class);
        }
        if (targetView == null) {
            targetView = MViewUtil.findTypeView(rootView, AbsListView.class);
        }

        if (targetView != null) {
            targetView.setPadding(0, 0, 0, MDisplayUtil.dp2px(tabBottomHeight, getResources()));
            targetView.setClipToPadding(false);
        }

    }


}
