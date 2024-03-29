package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CheckBox extends AbstractUI {

    private final Sprite on = FileHandler.getUi().get("CHECK_ON");
    public boolean checked;

    public CheckBox(boolean checked) {
        super(FileHandler.getUi().get("CHECK_OFF"));
        this.checked = checked;
    }

    public CheckBox(float x, float y, boolean checked) {
        super(FileHandler.getUi().get("CHECK_OFF"), x, y);
        this.checked = checked;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            if (checked) sb.draw(on, x, y, sWidth, sHeight);
            else sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    public void onClick() {
        checked = !checked;
    }
}
