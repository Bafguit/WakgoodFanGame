package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;
import com.fastcat.labyrintale.uis.TurnEffectText;
import org.graalvm.compiler.loop.MathUtil;

import static com.fastcat.labyrintale.handlers.FontHandler.FontType.BOLD;

public class TurnChangeEffect extends AbstractEffect {

    private final TurnEffectText text;
    private float alpha;

    public TurnChangeEffect(boolean isEnemy) {
        super(0, 0, 2);
        text = new TurnEffectText(isEnemy ? ++Labyrintale.battleScreen.round + " 번째 턴" : "턴 종료");
        alpha = 0;
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        float d = Gdx.graphics.getDeltaTime();
        if (duration < 0.5f) {
            alpha -= d;
        } else if (duration >= 1.5f) {
            alpha += d;
        }
        alpha = MathUtils.clamp(alpha, 0, 0.5f);
        text.img.setAlpha(alpha);
        text.fontData.font.getColor().a  = alpha * 2;
        text.render(sb);
    }
}