package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.uis.TurnEffectText;

public class FloorChangeEffect extends AbstractEffect {

    private final TurnEffectText text;
    private float alpha;

    public FloorChangeEffect(int floor) {
        super(0, 0, 3);
        text = new TurnEffectText("지하 " + floor + " 층");
        alpha = 0;
        duration = baseDuration = 3;
    }

    @Override
    protected void updateEffect() {
        float d = Labyrintale.tick * 2;
        if (duration < 0.5f) {
            alpha -= d;
        } else if (duration >= 2.5f) {
            alpha += d;
        }
        alpha = MathUtils.clamp(alpha, 0, 1);
        text.img.setAlpha(alpha);
        text.fontData.alpha = alpha;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        text.render(sb);
    }
}
