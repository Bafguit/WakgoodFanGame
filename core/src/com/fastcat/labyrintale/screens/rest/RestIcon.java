package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RestIcon extends AbstractUI {

    public Sprite icon;
    public RestButton b;

    public RestIcon(RestButton b, Sprite i) {
        super(FileHandler.ui.get("BORDER"));
        icon = i;
        this.b = b;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!b.over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if(b.showImg) sb.draw(icon, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
