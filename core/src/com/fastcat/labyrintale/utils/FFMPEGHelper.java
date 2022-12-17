package com.fastcat.labyrintale.utils;


import com.badlogic.gdx.files.FileHandle;
import net.indiespot.media.impl.FFmpeg;

public final class FFMPEGHelper {

    public static void prepare(FileHandle from, FileHandle to){
        from.copyTo(to);
        FFmpeg.FFMPEG_PATH = to.path();
    }
}
