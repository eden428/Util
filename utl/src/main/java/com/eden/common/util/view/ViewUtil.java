package com.eden.common.util.view;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/4/3.
 */
public class ViewUtil {
    public static boolean isHit(View v, int x, int y) {
        final int tx = (int) (ViewCompat.getTranslationX(v) + 0.5f);
        final int ty = (int) (ViewCompat.getTranslationY(v) + 0.5f);
        final int left = v.getLeft() + tx;
        final int right = v.getRight() + tx;
        final int top = v.getTop() + ty;
        final int bottom = v.getBottom() + ty;
        return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
    }

    /**
     * 扩大View的触摸和点击响应范围,最大不超过其父View范围
     *
     * @param view
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    public static void expandViewTouchDelegate(final View view, final int top,
                                               final int bottom, final int left, final int right) {

        ((View) view.getParent()).post(new Runnable() {
            @Override
            public void run() {
                Rect bounds = new Rect();
                view.setEnabled(true);
                view.getHitRect(bounds);

                bounds.top -= top;
                bounds.bottom += bottom;
                bounds.left -= left;
                bounds.right += right;

                TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                if (View.class.isInstance(view.getParent())) {
                    ((View) view.getParent()).setTouchDelegate(touchDelegate);
                }
            }
        });
    }

    /**
     * 切换密码框中的密码的可见性
     *
     * @param et
     * @param isShow
     */
    public static void setPwdVisibility(EditText et, boolean isShow) {
        if (isShow) {
            //设置EditText文本为可见的
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //设置EditText文本为隐藏的
            et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        et.postInvalidate();
        placeTheCursorAtTheEnd(et);
    }

    public static void placeTheCursorAtTheEnd(EditText et) {
        //切换后将EditText光标置于末尾
        CharSequence charSequence = et.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
}
