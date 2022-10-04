package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;


public class LogoImg extends AbstractUI {

    public LogoImg() {
        super(FileHandler.getBg().get("LOGO"));
        setPosition(0, 0);
        overable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        img.draw(sb);
    }
}
