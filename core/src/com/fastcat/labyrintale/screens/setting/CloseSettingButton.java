package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU;

public class CloseSettingButton extends AbstractUI {

    public CloseSettingButton(SettingScreen sc) {
        super(FileHandler.getUi().get("NEXT"));
        setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.9f);
        fontData = MAIN_MENU;
        text = "완료";
        showImg = false;
        screen = sc;
    }

    @Override
    protected void updateButton() {
        if (!over && showImg) showImg = false;
    }

    @Override
    protected void onOver() {
        showImg = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        Labyrintale.removeTempScreen(screen);
    }
}
