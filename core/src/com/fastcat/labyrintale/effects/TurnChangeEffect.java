package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.uis.TurnEffectText;

public class TurnChangeEffect extends AbstractEffect {

    private final TurnEffectText text;
    private float alpha;

    public TurnChangeEffect(int round) {
        super(0, 0, 2);
        text = new TurnEffectText(round + " 번째 라운드");
        alpha = 0;
        duration = baseDuration = 2;
    }

    @Override
    protected void updateEffect() {
        float d = Labyrintale.tick * 2;
        if (duration < 0.5f) {
            alpha -= d;
        } else if (duration >= 1.5f) {
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
