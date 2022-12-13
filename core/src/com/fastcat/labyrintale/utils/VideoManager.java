package com.fastcat.labyrintale.utils;

import java.io.File;
import java.io.IOException;
import net.indiespot.media.AudioRenderer;
import net.indiespot.media.RenderListener;
import net.indiespot.media.VideoPlayback;
import net.indiespot.media.impl.FFmpegVideoPlayback;
import net.indiespot.media.impl.OpenALAudioRenderer;
import net.indiespot.media.impl.OpenGLVideoRenderer;

public final class VideoManager {
    public static final String FFMPEG_NAME = "ffmpeg64.exe";
    private File videoFile;
    private VideoPlayback currentVideoPlayer;

    private RenderListener videoEndListener;

    private OpenGLVideoRenderer videoRenderer;

    private AudioRenderer audioRenderer;

    public VideoManager(File videoFile) throws IOException {
        this.videoFile = videoFile;

        videoRenderer = new OpenGLVideoRenderer(videoFile.getName());
        audioRenderer = new OpenALAudioRenderer();

        currentVideoPlayer = new FFmpegVideoPlayback(videoFile);

        currentVideoPlayer.setEndListener(videoEndListener);
    }

    public void start() {
        currentVideoPlayer.startVideo(videoRenderer, audioRenderer);
    }

    public boolean loopEnd() {
        return currentVideoPlayer != null && currentVideoPlayer.loopEnd();
    }

    public void update() {
        if (currentVideoPlayer != null) currentVideoPlayer.loop();
    }

    public void endLoop() {
        if (currentVideoPlayer != null) currentVideoPlayer.finalizeLoop();
    }
}
