package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.ImageHandler.MENU_SELECT;

public class TutorialButton extends AbstractUI {

    public TutorialButton() {
        super(MENU_SELECT, 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.4f);
        fontData = MAIN_MENU.cpy();
        text = "튜토리얼";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        if(!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        //Gdx.app.exit();
    }
}