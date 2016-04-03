package com.eden.common.util.common;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.eden.common.util.ContextProvider;

import java.util.Locale;

/**
 * Created by Administrator on 2016/4/3.
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
}
