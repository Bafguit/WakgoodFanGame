package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class LogoText extends AbstractUI {

    public LogoText() {
        super(FileHandler.ui.get("TITLE"));
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.6f);
        overable = false;
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
