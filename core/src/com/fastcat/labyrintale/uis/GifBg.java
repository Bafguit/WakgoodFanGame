package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.util.Arrays;

public class GifBg extends AbstractUI {

    private Animation<Sprite> animation;
    private final Sprite over = FileHandler.getBg().get("BG_OVER");
    private float elapsed = 0;
    private boolean hasBg = false;
    public float speed = 0.75f;

    public GifBg(Sprite sprite, String key) {
        super(sprite);
        setPosition(0, 0);
        overable = false;
        hasBg = true;
        Animation<Sprite> s = FileHandler.getGif().get(key);
        Object[] a = s.getKeyFrames();
        Sprite[] aa = Arrays.copyOf(a, a.length, Sprite[].class);
        animation = new Animation<>(s.getFrameDuration(), aa);
        animation.setPlayMode(s.getPlayMode());
    }

    public GifBg(String key) {
        super(FileHandler.getBg().get("BG_BLACK"));
        setPosition(0, 0);
        overable = false;
        Animation<Sprite> s = FileHandler.getGif().get(key);
        Object[] a = s.getKeyFrames();
        Sprite[] aa = Arrays.copyOf(a, a.length, Sprite[].class);
        animation = new Animation<>(s.getFrameDuration(), aa);
        animation.setPlayMode(s.getPlayMode());
    }

    public void setPlayMode(Animation.PlayMode mode) {
        animation.setPlayMode(mode);
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        elapsed += Labyrintale.tick * speed;
        sb.setColor(Color.WHITE);
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        if(hasBg) sb.draw(img, 0, 0, w, h);
        sb.draw(animation.getKeyFrame(elapsed), 0, 0, w, h);
        if(hasBg) sb.draw(over, 0, 0, w, h);
    }
}
