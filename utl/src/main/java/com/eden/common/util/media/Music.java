package com.eden.common.util.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/6/23.
 */
public class Music {
    private SoundPool soundPool;
    private final int MAX = 3;
    private int volume;
    private HashMap<Integer, Integer> soundIDs;
    private final int HIT = 1;
    private final int MISS = 2;
    private final int DIE = 3;

    public Music(Context context) {
        //当API为21时，可用//        SoundPool.Builder spb = new SoundPool.Builder();//        spb.setMaxStreams(10);//        spb.setAudioAttributes(null);    //转换音频格式//        SoundPool sp = spb.build();      //创建SoundPool对象//实例化soundPool

        //设置音量
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        volume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //建立声音键
        soundIDs = new HashMap<Integer, Integer>();
        soundPool = new SoundPool(MAX, AudioManager.STREAM_MUSIC, 100);

        //添加音乐
        soundIDs.put(HIT, soundPool.load("http://www.angelfire.com/planet/yuna163/2ndfloor.mp3", 1));
        soundIDs.put(HIT, soundPool.load("http://www.angelfire.com/punk/yuna104/oohboy.mp3 ", 2));

//        soundIDs.put(MISS, soundPool.load(context, R.raw.miss, 1));

    }

    //击中方法
    public void playHit() {
        if (soundPool != null)
            soundPool.play(HIT, volume, volume, 1, 0, 1f);
    }

    //未击中方法
    public void playMiss() {
        if (soundPool != null)
            soundPool.play(MISS, volume, volume, 1, 0, 1f);
    }

    //死亡方法
    public void playDie() {
        if (soundPool != null)
            soundPool.play(DIE, volume, volume, 1, 0, 1f);
    }

    //停止方法
    public void stop() {
        soundPool.release();
        soundPool = null;

    }

}
