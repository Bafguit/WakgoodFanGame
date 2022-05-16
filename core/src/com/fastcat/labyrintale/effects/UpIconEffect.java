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
    private float x, y, rw, rh;

    public UpIconEffect(float x, float y, Sprite img) {
        super(x, y, 1);
        this.img = img;
        rw = 100 * scale;
        rh = 100 * scale;
        this.x = x - 50 * scale;
        this.y = y - 50 * scale;
        this.img.setBounds(x, y, rw, rh);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        y += Gdx.graphics.getDeltaTime() * 100;
        img.setPosition(x, y);
        if(duration <= 0.5f) {
            alpha -= Gdx.graphics.getDeltaTime() * 2f;
            if(alpha < 0) alpha = 0;
        }
        img.draw(sb, alpha);
    }
}
