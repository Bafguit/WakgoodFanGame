package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class ReturnToMainButton extends AbstractUI {

    public ReturnToMainButton(SettingScreen sc) {
        super(FileHandler.getUi().get("SETTING_MAIN"));
        setPosition(990 * InputHandler.scale - sWidth / 2, 190 * InputHandler.scale - sHeight / 2);
        screen = sc;
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        SoundHandler.fadeOutAll();
        Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen);
    }
}
