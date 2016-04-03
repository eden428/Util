package com.eden.common.util;

import android.content.Context;

/**
 * Created by Administrator on 2016/4/3.
 */
public class ContextProvider {


    public Context getContext() {
        if (context == null)
            throw new RuntimeException("请先调用ContextProvider.getInstance().setContext(context)");
        return context;
    }

    /**
     * 可以在Application的onCreate()中设置
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    private ContextProvider() {

    }

    public static ContextProvider getInstance() {
        return Builder.INSTANCE;
    }


    public static class Builder {
        public static ContextProvider INSTANCE = new ContextProvider();
    }
}
