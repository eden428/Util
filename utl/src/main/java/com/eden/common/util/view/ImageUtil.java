package com.eden.common.util.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.eden.common.util.ContextProvider;
import com.eden.common.util.file.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/3.
 */
public class ImageUtil {

    /**
     * http://developer.android.com/intl/zh-cn/training/displaying-bitmaps/load-bitmap.html
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap getThumbnail(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap getThumbnail(int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(ContextProvider.getInstance().getContext().getResources(), resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(ContextProvider.getInstance().getContext().getResources(), resId, options);
    }

    public static String saveBitmap(Bitmap bitmap, String path) {
        FileOutputStream fo = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

            // you can create a new file name "test.jpg" in sdcard folder.
            File f = new File(path);
            FileUtil.deleteFileSafely(f);
            f.createNewFile();
            // write the bytes in file
            fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            // remember close de FileOutput
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (fo != null)
                    fo.close();
            } catch (Exception e) {
            }
        }
        return path;
    }
}
