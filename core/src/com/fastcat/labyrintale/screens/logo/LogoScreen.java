package com.fastcat.labyrintale.screens.logo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.video.CommonVideoPlayerDesktop;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.badlogic.gdx.video.VideoPlayerDesktop;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fastcat.labyrintale.utils.video.LibGdxVideoRenderer;
import net.indiespot.media.RenderListener;
import net.indiespot.media.VideoPlayback;
import net.indiespot.media.impl.FFmpegVideoPlayback;
import net.indiespot.media.impl.OpenALAudioRenderer;
import net.indiespot.media.impl.OpenGLVideoRenderer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

public class LogoScreen extends AbstractScreen {

    public boolean isDone = false;
    private boolean create = true;
    private boolean go = true;
    private int mode = 0;
    private float vol = 0;
    private float realVol = 0;
    private float timer = 0;
    private float color = 0;
    private int dup = 1;
    private final AbstractUI.TempUI logo;
    private final Sprite back;
    private VideoPlayer videoPlayer;

    private VideoPlayback videoPlayback;

    private LibGdxVideoRenderer videoRenderer;

    public LogoScreen() {

        cType = ControlPanel.ControlType.HIDE;
        setBg(FileHandler.getBg().get("BG_BLACK"));
        back = FileHandler.getBg().get("BG_GREY");
        back.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        logo = new AbstractUI.TempUI(FileHandler.getUi().get("LOGO"));
        logo.setPosition(
                Gdx.graphics.getWidth() * 0.5f - logo.sWidth * 0.5f,
                Gdx.graphics.getHeight() * 0.5f - logo.sHeight * 0.5f);
        //SoundHandler.playMusic("LOGO", false, false);

        try {
            videoPlayback = new FFmpegVideoPlayback(Gdx.files.internal("video/logo.webm").file());
            videoPlayback.setEndListener(new RenderListener() {
                @Override
                public void onRender() {
                    isDone = true;
                }
            });
            videoRenderer = new LibGdxVideoRenderer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void onCreate() {

    }

    @Override
    public void update() {
        /*timer += Labyrintale.tick / dup;
        if (timer >= 3 && mode == 0) {
            dup = 2;
            mode = 1;
        } else if (mode == 1) {
            color += Labyrintale.tick / dup;
            if (color > 1) color = 1;
            if (timer >= 4) {
                mode = 2;
                dup = 1;
            }
        } else {
            if (timer >= 5) isDone = true;
        }*/

        if (!isDone) {
            if (InputHandler.isLeftClick) {
                isDone = true;
            }
            if(vol < 1) {
                vol += Labyrintale.tick * 2;
                if(vol >= 1) vol = 1;
            }
        } else {
            if(vol > 0) {
                vol -= Labyrintale.tick;
                if(vol <= 0) vol = 0;
            }
            if (go) {
                go = false;
                SoundHandler.fadeOutAll();
                Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
            }
        }

        realVol = MathUtils.clamp(SettingHandler.setting.volumeBgm * vol * 0.8f, 0, 1);
        //videoPlayer.setVolume(realVol);
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setColor(Color.WHITE);
        //back.draw(sb, color);

        if(create) {
            create = false;
            try {
                videoPlayback.startVideo(videoRenderer, new OpenALAudioRenderer());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            /*
            try {
                videoPlayer = VideoPlayerCreator.createVideoPlayer();
                videoPlayer.setOnCompletionListener(file -> isDone = true);
                videoPlayer.play(FileHandler.getVideo().get("LOGO"));

                videoPlayer.setLooping(false);


            } catch (Exception e) {
                isDone = true;
                e.printStackTrace();
            }

             */
        }

        if(videoPlayback.loopEnd()){
            videoPlayback.loop();
            Texture t = videoRenderer.getTexture();
            if(t != null) {
                sb.draw(t, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
        }else{
            videoPlayback.finalizeLoop();
        }
        //logo.render(sb);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
    }
}
