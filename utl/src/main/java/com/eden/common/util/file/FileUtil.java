package com.eden.common.util.file;

import java.io.File;

/**
 * Created by Administrator on 2016/4/3.
 */
public class FileUtil {
    /**
     * 安全删除文件.
     *
     * @param file
     * @return
     */
    public static boolean deleteFileSafely(File file) {
        try {
            if (file != null) {
                String tmpPath = file.getParent() + File.separator + System.currentTimeMillis();
                File tmp = new File(tmpPath);
                file.renameTo(tmp);
                return tmp.delete();
            }
        } catch (Exception e) {
        }
        return false;
    }
}
