package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;


public class ItemPanel extends AbstractUI {

    public AbstractItem item;

    public ItemPanel() {
        super(FileHandler.getUi().get("BORDER"));
        clickable = false;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(item);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return item != null ? item.key : null;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            if (item != null) sb.draw(item.img, x, y, sWidth, sHeight);
        }
    }
}
