package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class UpIconEffect extends AbstractEffect {

    private final Sprite img;
    private float alpha = 0.7f;
    private float sc = 1.0f;
    private final float x, y, rw, rh;

    public UpIconEffect(float x, float y, Sprite img) {
        super(x, y, 1);
        this.img = img;
        rw = 80 * scale;
        rh = 80 * scale;
        this.x = x;
        this.y = y;
        this.img.setBounds(x - 40 * scale, y - 40 * scale, rw, rh);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(duration != baseDuration) {
            alpha -= Gdx.graphics.getDeltaTime() * 0.7f;
            sc -= Gdx.graphics.getDeltaTime() * 0.5f;
            if(alpha < 0) alpha = 0;
            img.setBounds(x - rw * 0.5f * sc, img.getY() + Gdx.graphics.getDeltaTime() * 100,
                    rw * sc, rh * sc);
        }
        img.draw(sb, alpha);
    }
}
