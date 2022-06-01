package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class UpIconEffect extends AbstractEffect {

    private final Sprite img;
    private float alpha = 0.8f;
    private float x, y, rw, rh;

    public UpIconEffect(float x, float y, Sprite img) {
        super(x, y, 1);
        this.img = new Sprite(img.getTexture());
        rw = 100 * scale;
        rh = 100 * scale;
        this.x = x - 50 * scale;
        this.y = y - 50 * scale;
        this.img.setBounds(x, y, rw, rh);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        y += Labyrintale.tick * 100;
        img.setPosition(x, y);
        if(duration <= 0.5f) {
            float f = Labyrintale.tick * 1.6f;
            if(alpha <= f) isDone = true;
            alpha = MathUtils.clamp(alpha - f, 0, 0.7f);
        }
        img.draw(sb, alpha);
    }
}
