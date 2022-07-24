package com.fastcat.labyrintale.screens.charinfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CharItemIcon extends AbstractUI {

    public AbstractItem skill;

    public CharItemIcon(AbstractItem s) {
        super(FileHandler.getUi().get("BORDER_M"));
        skill = s;
        clickable = false;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
