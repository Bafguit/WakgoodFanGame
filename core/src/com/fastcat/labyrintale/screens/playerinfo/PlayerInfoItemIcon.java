package com.fastcat.labyrintale.screens.playerinfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class PlayerInfoItemIcon extends AbstractUI {

    private final Sprite border = FileHandler.getUi().get("BORDER_R");
    public AbstractItem skill;

    public PlayerInfoItemIcon(AbstractItem s) {
        super(FileHandler.getUi().get("BORDER"));
        skill = s;
        clickable = false;
        subDown = true;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : null;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(skill.rarity == AbstractItem.ItemRarity.STARTER ? border : img, x, y, sWidth, sHeight);
        }
    }
}