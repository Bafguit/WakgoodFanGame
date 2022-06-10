package com.fastcat.labyrintale.screens.logo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.effects.LogoEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.deckview.BgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.io.FileNotFoundException;

public class LogoScreen extends AbstractScreen {

    public VideoPlayer videoPlayer;
    public boolean isDone = false;

    public LogoScreen() {
        cType = ControlPanel.ControlType.HIDE;
        setBg(FileHandler.ui.get("FADE"));
        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        try {
            videoPlayer.play(FileHandler.video.get("LOGO"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //effectHandler.effectList.addLast(new LogoEffect());
        videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle file) {
                isDone = true;
            }
        });
    }

    @Override
    public void update() {
        videoPlayer.update();
        if(InputHandler.isLeftClick && !isDone) isDone = true;
        if(isDone) {
            Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen, 2.0f);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        Texture frame = videoPlayer.getTexture();
        if (frame != null) {
            sb.draw(frame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        videoPlayer.stop();
        videoPlayer.dispose();
    }

    @Override
    public void dispose() {

    }
}