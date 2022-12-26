package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class TurnEffectText extends AbstractUI {

    public TurnEffectText(String text) {
        super(FileHandler.getUi().get("TURN_BG"));
        setPosition(0, 936 * InputHandler.scale - sHeight / 2);
        img.setBounds(x, y, sWidth, sHeight);
        fontData = FontHandler.TURN_CHANGE;
        this.text = text;
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (fontData != null) {
                img.draw(sb);
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
