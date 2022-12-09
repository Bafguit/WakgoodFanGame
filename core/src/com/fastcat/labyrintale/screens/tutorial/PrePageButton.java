package com.fastcat.labyrintale.screens.tutorial;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class PrePageButton extends AbstractUI {

    private TutorialScreen sc;

    public PrePageButton(TutorialScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.9f);
        fontData = BUTTON;
        text = "이전";
        this.sc = sc;
        clicked = false;
    }

    @Override
    protected void updateButton() {
        clickable = sc.index > 0;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (clickable) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        sc.index--;
    }
}
