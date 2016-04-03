package com.eden.common.util.intent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Administrator on 2016/4/3.
 */
public class IntentUtil {
    public static void openFile(Context context, String filePath) {
        try {
            File file = new File(filePath);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(fixedFormat(filePath));
            String type = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            if (type == null) {
                type = "*/*";
            }
            intent.setDataAndType(Uri.fromFile(file), type);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    public static void openFolder(Context context, String file) {
        try {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setDataAndType(Uri.fromFile(new File(file).getParentFile()), "file/*");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    private static String fixedFormat(String filePath) {
        if (filePath.contains(".")) {
            String format = filePath.substring(filePath.lastIndexOf("."));
            filePath = filePath.replace(format, format.toLowerCase());
        }
        return Uri.fromFile(new File(filePath)).toString();
    }

    /**
     * 安装chineseOnMars
     *
     * @param context
     */
    public static void installApk(Context context, String fielPath) {
        try {
            File apk = new File(fielPath);
            if (apk.exists() && apk.isFile()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + apk.getAbsolutePath()), "application/vnd.android.package-archive");
                context.startActivity(intent);
            }
        } catch (Exception e) {
        }

    }
}
