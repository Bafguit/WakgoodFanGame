package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class OptionPanel extends AbstractUI {
    public OptionPanel() {
        super(FileHandler.ui.get("OPTION_PANEL"));
        setPosition((Gdx.graphics.getWidth() - sWidth) * 0.5f, (Gdx.graphics.getHeight() - sHeight) * 0.5f);
    }
}
