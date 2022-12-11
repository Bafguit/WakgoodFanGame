package com.fastcat.labyrintale.screens.mainmenu;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.loading.LoadingScreen;

public class LoadButton extends AbstractUI {

    public LoadButton() {
        super(FileHandler.getUi().get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.8f - sWidth / 2, Gdx.graphics.getHeight() * 0.385f);
        fontData = new FontData(MEDIUM, 53, false, false);
        text = "불러오기";
        showImg = false;
        overable = SaveHandler.hasSave;
    }

    @Override
    protected void updateButton() {
        overable = SaveHandler.hasSave;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) fontData.color = Color.GRAY;
            else fontData.color = WHITE;

            renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        Labyrintale.fadeOutAndChangeScreen(new LoadingScreen(false));
    }
}
