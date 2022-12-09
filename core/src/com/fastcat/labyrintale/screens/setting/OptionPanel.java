package com.fastcat.labyrintale.screens.setting;

import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class OptionPanel extends AbstractUI {
    public OptionPanel() {
        super(FileHandler.getUi().get("SETTING"));
        setPosition(0, 0);
        overable = false;
    }
}
