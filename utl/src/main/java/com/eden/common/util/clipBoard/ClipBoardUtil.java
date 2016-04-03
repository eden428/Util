package com.eden.common.util.clipBoard;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;

import com.eden.common.util.ContextProvider;

/**
 * Created by Administrator on 2016/4/3.
 */
public class ClipBoardUtil {

    public static void copyToClipboard(String text) {
        copyToClipboard(ContextProvider.getInstance().getContext().getPackageName(), text);
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text
     */
    public static void copyToClipboard(String tag, String text) {
        ClipboardManager cbm = (ClipboardManager) ContextProvider.getInstance().getContext().getSystemService(Activity.CLIPBOARD_SERVICE);
        cbm.setPrimaryClip(ClipData.newPlainText(tag, text));
    }

    public static String pasteData() {
        ClipboardManager cbm = (ClipboardManager) ContextProvider.getInstance().getContext().getSystemService(Activity.CLIPBOARD_SERVICE);
        ClipData.Item item = cbm.getPrimaryClip().getItemAt(0);
        return item.toString();
    }

    public static Object findDataByLaber(String label) {
        ClipboardManager cbm = (ClipboardManager) ContextProvider.getInstance().getContext().getSystemService(Activity.CLIPBOARD_SERVICE);
        ClipData data = cbm.getPrimaryClip();
        if (data.getDescription().getLabel().equals(label)) {
            // TODO: 根据具体需求实现

        }
        return null;
    }

}
