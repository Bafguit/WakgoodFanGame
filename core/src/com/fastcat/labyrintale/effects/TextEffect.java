package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.EffectPublicText;

import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class TextEffect extends AbstractEffect {

    private EffectPublicText text;

    public TextEffect(float x, float y, String s) {
        super(x, y, 0.5f);
        text = new EffectPublicText(FileHandler.ui.get("MENU_SELECT"), 300, 60);
        text.fontData = new FontHandler.FontData(MEDIUM, 32, true);
        text.text = s;
        text.setPosition(x - text.sWidth / 2, y - text.sHeight / 2);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(duration <= 0.15f) {
            text.fontData.font.getColor().a -= Gdx.graphics.getDeltaTime() / 0.15f;
        }
        text.render(sb);
    }

    @Override
    public void dispose() {
        text.dispose();
    }
}
