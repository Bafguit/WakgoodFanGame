package com.fastcat.labyrintale.screens.mainmenu;

import static com.fastcat.labyrintale.handlers.FontHandler.FontData;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class ExitButton extends AbstractUI {

    private final Color fColor = Color.GRAY.cpy();
    private final float aa = fColor.r;
    private float a = fColor.r;

    public ExitButton() {
        super(FileHandler.getUi().get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.125f);
        fontData = new FontData(MEDIUM, 53, false, false);
        text = "종료";
        showImg = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) {
                a -= Labyrintale.tick * 4;
                if (a <= aa) a = aa;
            } else {
                a += Labyrintale.tick * 4;
                if (a >= 1) a = 1;
            }

            fColor.set(a, a, a, 1);
            fontData.color = fColor;

            renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    public void show() {
        a = aa;
    }

    @Override
    protected void onClick() {
        logger.log("Shutting Down...");
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        fontData.dispose();
    }
}
