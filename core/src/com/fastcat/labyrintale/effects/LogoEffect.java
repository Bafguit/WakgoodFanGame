package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import java.io.FileNotFoundException;

public class LogoEffect extends AbstractEffect {

    public FileHandle video = Gdx.files.internal("video/logo.webm");

    public LogoEffect() {
        super(0, 0, 6f);
        try {
            Labyrintale.videoPlayer.play(video);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        Labyrintale.videoPlayer.update();
        Texture frame = Labyrintale.videoPlayer.getTexture();
        if (frame != null) sb.draw(frame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(InputHandler.isLeftClick && !isDone) isDone = true;
        if(isDone) {
            Labyrintale.fading = true;
            Labyrintale.game.setScreen(Labyrintale.mainMenuScreen);
        }
    }

    @Override
    public void dispose() {

    }
}
