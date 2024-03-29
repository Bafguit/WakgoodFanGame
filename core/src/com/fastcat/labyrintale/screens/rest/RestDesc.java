package com.fastcat.labyrintale.screens.rest;

import static com.fastcat.labyrintale.handlers.FontHandler.INFO_NAME;
import static com.fastcat.labyrintale.handlers.FontHandler.renderColorCenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RestDesc extends AbstractUI {

    private final RestButton b;

    public RestDesc(String d, RestButton b) {
        super(FileHandler.getUi().get("WAY_SELECT"));
        text = d;
        this.b = b;
        showImg = false;
        fontData = INFO_NAME;
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            renderColorCenter(sb, fontData, text, x, y + sHeight / 2, sWidth);
        }
    }
}
