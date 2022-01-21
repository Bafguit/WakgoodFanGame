package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEffect;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class FlashEffect extends AbstractEffect {

    private final Sprite img;
    private float alpha = 0.7f;
    private float sc = 1.5f;
    private final float x, y, rw, rh;

    public FlashEffect(float x, float y, Sprite img, float scale) {
        super(x, y, 1);
        this.img = img;
        this.x = x;
        this.y = y;
        rw = img.getWidth() * scale;
        rh = img.getHeight() * scale;
        this.img.setBounds(x - img.getWidth() * 0.5f * scale, y - img.getHeight() * 0.5f * scale, rw, rh);

    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(duration != baseDuration) {
            alpha -= Gdx.graphics.getDeltaTime() * 0.7f;
            sc -= Gdx.graphics.getDeltaTime() * 0.5f;
            if(alpha < 0) alpha = 0;
            img.setBounds(x - rw * 0.5f * scale, y - rh * 0.5f * scale, rw * sc, rh * sc);
        }
        img.draw(sb, alpha);
    }
}
