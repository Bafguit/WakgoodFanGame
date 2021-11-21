package com.fastcat.labyrintale.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.LogHandler;

public class ScaleButton2 extends AbstractUI {

    private static final LogHandler logger = new LogHandler(ScaleButton2.class.getName());
    private static final String IMG = "badlogic.jpg";

    public ScaleButton2() {
        super(IMG, 200, Gdx.graphics.getHeight() - 200);
        setText("1600");
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
        Gdx.graphics.setWindowedMode(1600, 900);
    }
}
