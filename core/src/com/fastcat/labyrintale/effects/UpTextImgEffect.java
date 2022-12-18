package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractUI;

public class UpTextImgEffect extends AbstractEffect {

    private final AbstractUI.TempUI img;
    private final float x;
    private float alpha = 1;
    private float y;

    public UpTextImgEffect(float x, float y, Sprite img) {
        super(x, y, 1f);
        duration = baseDuration = 1;
        this.img = new AbstractUI.TempUI(new Sprite(img.getTexture()));
        this.x = x - this.img.sWidth / 2;
        this.y = y;
        this.img.img.setBounds(this.x, this.y, this.img.sWidth, this.img.sHeight);
    }

    @Override
    protected void updateEffect() {
        y += Labyrintale.tick * 100;
        img.img.setPosition(x, y + Labyrintale.tick * 100);
        if (duration <= baseDuration / 2) {
            float f = Labyrintale.tick / baseDuration * 2;
            alpha -= f;
            if (alpha < 0) {
                alpha = 0;
                isDone = true;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        img.img.draw(sb, alpha);
    }
}
