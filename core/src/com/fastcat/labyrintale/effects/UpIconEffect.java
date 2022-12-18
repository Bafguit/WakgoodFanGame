package com.fastcat.labyrintale.effects;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;

public class UpIconEffect extends AbstractEffect {

    private final Sprite img;
    private final float x;
    private final float rw;
    private final float rh;
    private float alpha = 0.9f;
    private float y;

    public UpIconEffect(float x, float y, Sprite img) {
        super(x, y, 1.5f);
        duration = baseDuration = 1;
        this.img = new Sprite(img.getTexture());
        rw = 100 * scale;
        rh = 100 * scale;
        this.x = x - 50 * scale;
        this.y = y - 50 * scale;
        this.img.setBounds(x, y, rw, rh);
    }

    @Override
    protected void updateEffect() {
        y += Labyrintale.tick * 100;
        img.setPosition(x, y);
        if (duration <= baseDuration / 2) {
            float f = Labyrintale.tick / baseDuration * 2;
            if (alpha <= f) isDone = true;
            alpha = MathUtils.clamp(alpha - f, 0, 0.7f);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        img.draw(sb, alpha);
    }
}
