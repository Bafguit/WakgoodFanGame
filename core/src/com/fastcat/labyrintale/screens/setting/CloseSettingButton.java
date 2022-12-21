package com.fastcat.labyrintale.screens.setting;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class CloseSettingButton extends AbstractUI {

    public CloseSettingButton(SettingScreen sc) {
        super(FileHandler.getUi().get("SETTING_CLOSE"));
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 1075 * InputHandler.scale);
        screen = sc;
    }

    @Override
    protected void onClick() {
        SettingHandler.save();
        Labyrintale.closeSetting();
    }
}
