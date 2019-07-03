package com.example.blix.util;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class SpeedyGridLinearLayoutManager extends GridLayoutManager {
    private static final float MILLISECONDS_PER_INCH = 1f; //default is 25f (bigger = slower)

    public SpeedyGridLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SpeedyGridLinearLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public SpeedyGridLinearLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return super.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                //return super.calculateSpeedPerPixel(displayMetrics) * 2;
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
