package com.xcelder.dynamiccontainerlayout.views;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcelder on 30/12/2017.
 */

public class DynamicContainerLayout extends RelativeLayout {

    Context mContext;

    //values
    List<Integer> layoutIds = new ArrayList<>();
    int initChildCount = 0;
    int currentIndex = 0;

    public DynamicContainerLayout(Context context) {
        super(context);
        init(context);
    }

    public DynamicContainerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DynamicContainerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        isInner = true;
        post(onCreateView);
    }

    Runnable onCreateView = new Runnable() {
        @Override
        public void run() {
            if (currentIndex == 0)
                inflateViewByIndex(0);
        }
    };

    boolean isInner = false;
    @Override
    public boolean post(final Runnable action) {
        if (isInner) {
            isInner = false;
            return super.post(action);
        } else {
            return postDelayed(new Runnable() {
                @Override
                public void run() {
                    DynamicContainerLayout.super.post(action);
                }
            },1);
        }
    }

    public void inflateViewByIndex(final int index) {
        currentIndex = index;
        post(new Runnable() {
            @Override
            public void run() {
                if (index != -1 && index < layoutIds.size()) {
                    removeAllViews();
                    addView(LayoutInflater.from(mContext)
                            .inflate(layoutIds.get(index),
                                    DynamicContainerLayout.this,
                                    false));

                }
            }
        });

    }

    public void inflateViewById(@LayoutRes int id) {
        inflateViewByIndex(layoutIds.indexOf(id));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("onMeasure", "childs: " + getChildCount());
        if (initChildCount == 0) {
            if (getChildCount() >= 1 || layoutIds.size() == 0) {
                initChildCount = getChildCount();
                for (int i = 0; i < getChildCount(); i++) {
                    if (getChildAt(i) instanceof ViewStub)
                        layoutIds.add(((ViewStub) getChildAt(i)).getLayoutResource());
                }
            }

            removeAllViews();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
