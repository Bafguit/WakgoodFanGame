package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class LogoText extends AbstractUI {

    public LogoText() {
        super(FileHandler.ui.get("MENU_SELECT"), 0, 0, 300, 60);
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.7f);
        fontData = LOGO;
        text = "W의 미궁";
        showImg = false;
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(fontData != null) {
                renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
