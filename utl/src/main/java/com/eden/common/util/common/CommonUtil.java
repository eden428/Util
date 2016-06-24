package com.eden.common.util.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;

import com.eden.common.util.ContextProvider;
import com.eden.common.util.display.DisplayUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

/**
 * Created by eden on 2016/4/3.
 */
public class CommonUtil {
    /**
     * 获取手机号 需要权限
     *
     * @return
     */
    public static String getPhoneNumber() {
        TelephonyManager tMgr = (TelephonyManager) ContextProvider.getInstance().getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tMgr.getLine1Number();
    }

    public static boolean isZn() {
        Locale locale = ContextProvider.getInstance().getContext().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }

    public static String getIMEI() {
        String imei;
        try {
            imei = ((TelephonyManager) ContextProvider.getInstance().getContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception e) {
            imei = "11111111";
        }
        return imei;
    }

    /**
     * 判断应用是否处于后台状态
     *
     * @return
     */
    public static boolean isBackground() {
        ActivityManager am = (ActivityManager) ContextProvider.getInstance().getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(ContextProvider.getInstance().getContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否有网络
     *
     * @return
     */
    public static boolean isNetWorkAvilable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextProvider.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return false;
        } else {
            return true;
        }
    }

    public static String getStringFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(ContextProvider.getInstance().getContext().getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Uri getUriFromRes(int id) {
        Resources resources = ContextProvider.getInstance().getContext().getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id));
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = DisplayUtil.getScreenWidth();
        int height = DisplayUtil.getScreenHeight();
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取meta_data数据
     * @param context
     * @param key
     * @return
     */
    public static String getManifestString(Context context, String key) {
        return getBundle(context).getString(key);
    }

    private static Bundle getBundle(Context context) {
        Bundle bundle;
        try {
            bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bundle;
    }

}
