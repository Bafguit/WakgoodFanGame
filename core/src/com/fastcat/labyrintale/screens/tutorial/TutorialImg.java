package com.fastcat.labyrintale.screens.tutorial;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class TutorialImg extends AbstractUI {

    public Sprite image;

    public TutorialImg() {
        super(FileHandler.getBg().get("BG_BLACK"));
        setPosition(0, 0);
        overable = false;
        image = FileHandler.getBg().get("BG_BLACK");
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(image, x, y, sWidth, sHeight);
    }
}
