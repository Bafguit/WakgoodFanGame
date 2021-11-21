package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.LogHandler;

public class ScaleButton extends AbstractUI {

    private static final LogHandler logger = new LogHandler(ScaleButton.class.getName());
    private static final String IMG = "badlogic.jpg";

    public ScaleButton() {
        super(IMG, 200, Gdx.graphics.getHeight() - 100);
        setText("1920");
        height = fontData.font.getLineHeight();
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
        Gdx.graphics.setWindowedMode(1920, 1080);
    }
}
