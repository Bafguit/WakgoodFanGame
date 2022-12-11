package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class ItemPanel extends AbstractUI {

    public TempUI paper = new TempUI(FileHandler.getUi().get("BORDER_ADV"));
    public AbstractItem item;
    boolean adv = false;

    public ItemPanel() {
        super(FileHandler.getUi().get("BORDER_M"));
        clickable = false;
    }

    @Override
    protected void updateButton() {
        if (over && item != null) {
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
            if (adv)
                sb.draw(
                        paper.img,
                        x + sWidth / 2 - paper.sWidth / 2,
                        y - 68 * InputHandler.scale,
                        paper.sWidth,
                        paper.sHeight);
            if (item != null) {
                sb.draw(item.img, x, y, sWidth, sHeight);
                sb.setColor(item.getRarityColor());
                if (!adv && item.rarity != AbstractItem.ItemRarity.STARTER) sb.draw(img, x, y, sWidth, sHeight);
                sb.setColor(Color.WHITE);
            }
        }
    }
}
