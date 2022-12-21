package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class ExitGameButton extends AbstractUI {

    public ExitGameButton(SettingScreen sc) {
        super(FileHandler.getUi().get("SETTING_EXIT"));
        setPosition(1570 * InputHandler.scale - sWidth / 2, 190 * InputHandler.scale - sHeight / 2);
        screen = sc;
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        if(InputHandler.isDesktop) Gdx.app.exit();
        else System.exit(0);
    }
}
