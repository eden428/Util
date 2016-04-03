package com.eden.common.util.media;

import android.media.MediaMetadataRetriever;
import android.net.Uri;

import com.eden.common.util.ContextProvider;

/**
 * Created by eden on 2016/4/3.
 */
public class MediaUtil {
    //common

    /**
     * This method may be time-consuming.
     *
     * @param filePath
     * @return
     */
    public static long getDuration(String filePath) {
        long duration = 0;
        try {
            Uri uri = Uri.parse(filePath);
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(ContextProvider.getInstance().getContext(), uri);//
            String s = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            duration = Long.parseLong(s);
        } catch (Exception e) {
        }
        return duration;
    }

    //Audio

    //video


}
