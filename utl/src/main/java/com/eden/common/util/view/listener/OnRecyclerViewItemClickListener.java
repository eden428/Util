package com.eden.common.util.view.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/13.
 */
public abstract class OnRecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView view;
    private GestureDetectorCompat gestureDetectorCompat;

    public OnRecyclerViewItemClickListener(RecyclerView view) {
        this.view = view;
        gestureDetectorCompat = new GestureDetectorCompat(view.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = view.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = view.getChildViewHolder(child);
                onItemClick(vh);
            }
            return true;
        }

        //  长点击事件，本例不需要不处理
        @Override
        public void onLongPress(MotionEvent e) {
            View child = view.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = view.getChildViewHolder(child);
                onItemLongClick(vh);
            }
        }
    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);
}
