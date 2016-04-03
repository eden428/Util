package com.eden.common.util.display;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.eden.common.util.ContextProvider;

/**
 * Created by eden on 2016/4/3.
 */
public class DisplayUtil {

    public static int dp2px(float dp) {
        float scale = ContextProvider.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5);
    }

    public static int px2dp(float px) {
        float scale = ContextProvider.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5);
    }

    public static int sp2px(float sp) {
        float scale = ContextProvider.getInstance().getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5);
    }

    public static int px2sp(float px) {
        float scale = ContextProvider.getInstance().getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5);
    }

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) ContextProvider.getInstance().getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    //包含状态栏
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) ContextProvider.getInstance().getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getStatusHeight() {
        int result = 0;
        int resultId = ContextProvider.getInstance().getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resultId > 0) {
            result = ContextProvider.getInstance().getContext().getResources().getDimensionPixelSize(resultId);
        }

        return result;


    }

    private static int getStatusHeightByReflect() {
        int statusHeight = 0;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = ContextProvider.getInstance().getContext().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
        }
        return statusHeight;
    }
}
