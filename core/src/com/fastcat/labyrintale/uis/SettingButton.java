package com.fastcat.labyrintale.uis;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class SettingButton extends AbstractUI {

    public SettingButton(float x, float y) {
        super(FileHandler.getUi().get("SETTING_B"), x, y);
        isPixmap = true;
    }

    @Override
    protected void onClick() {
        if (!Labyrintale.setting) {
            Labyrintale.openSetting();
        }
    }
}
