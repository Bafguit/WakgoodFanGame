package com.fastcat.labyrintale.screens.logo;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class LogoImage extends AbstractUI {
    public LogoImage() {
        super(FileHandler.getUi().get("LOGO"));
        setPosition((Gdx.graphics.getWidth() - sWidth) * 0.5f, (Gdx.graphics.getHeight() - sHeight) * 0.5f);
        overable = false;
    }
}
