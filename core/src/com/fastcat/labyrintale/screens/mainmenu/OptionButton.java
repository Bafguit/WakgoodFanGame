package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class OptionButton extends AbstractUI {

    public OptionButton() {
        super(FileHandler.ui.get("MENU_SELECT"), 0, 0, 300, 50);
        setPosition(Gdx.graphics.getWidth() * 0.7f - sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        fontData = MAIN_MENU;
        text = "설정";
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
