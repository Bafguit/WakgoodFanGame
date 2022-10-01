package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.mainMenuScreen;
import static com.fastcat.labyrintale.Labyrintale.riskSelectScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class RiskMenuButton extends AbstractUI {

    public RiskMenuButton() {
        super(FileHandler.getUi().get("BACK"));
        setPosition(Gdx.graphics.getWidth() * 0.95f - sWidth, Gdx.graphics.getHeight() * 0.05f);
        fontData = MAIN_MENU;
        text = "제약";
        showImg = false;
    }

    @Override
    protected void updateButton() {
        //if (!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        //showImg = true;
    }

    @Override
    protected void onClick() {
        Labyrintale.addTempScreen(riskSelectScreen);
    }
}
