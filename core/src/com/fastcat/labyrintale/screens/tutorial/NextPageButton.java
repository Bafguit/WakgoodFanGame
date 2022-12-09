package com.fastcat.labyrintale.screens.tutorial;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class NextPageButton extends AbstractUI {

    private TutorialScreen sc;

    public NextPageButton(TutorialScreen sc) {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = BUTTON;
        text = "다음";
        this.sc = sc;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (overable && over) sb.setColor(WHITE);
        else sb.setColor(LIGHT_GRAY);
        sb.draw(img, x, y, sWidth, sHeight);
        renderKeywordCenter(sb, fontData, sc.index < sc.max ? text : "닫기", x, y + sHeight / 2, sWidth, sHeight);
    }

    @Override
    protected void onClick() {
        if (sc.index < sc.max) {
            sc.index++;
        } else {
            Labyrintale.closeTutorial();
        }
    }
}
