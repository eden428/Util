package com.eden.common.util.serialize;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/4/3.
 */
public class SerializeUtil {
    /**
     * 保存对象
     *
     * @param ser
     * @param file
     * @throws IOException
     */
    public synchronized static boolean saveObject(Object ser, String file, Context instance) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = instance.openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 读取对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public synchronized static Object readObject(String file, Context instance) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = instance.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

}
