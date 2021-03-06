package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.InputHandler;

public class HitEffect extends AbstractEffect {

    private final Sprite img;
    private float alpha = 1;

    public HitEffect(float x, float y, Sprite img) {
        this(x, y, img, 1);
    }

    public HitEffect(float x, float y, Sprite img, float scale) {
        super(x, y, 0.6f);
        this.img = new Sprite(img.getTexture());
        float rw = img.getWidth() * InputHandler.scale * scale, rh = img.getHeight() * InputHandler.scale * scale;
        this.img.setBounds(x - rw * 0.5f, y - rh * 0.5f, rw, rh);

    }

    @Override
    protected void updateEffect() {
        if (duration <= 0.4f) {
            alpha -= Labyrintale.tick * 2.5F;
            if (alpha < 0) alpha = 0;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        img.draw(sb, alpha);
    }
}
