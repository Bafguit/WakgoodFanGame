package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CharDeckButton extends AbstractUI {

    public ShopTakeScreen sc;
    public AbstractSkill skill;
    public AbstractSkill toSkill;
    public int index;

    public CharDeckButton(ShopTakeScreen screen, int index, AbstractSkill s) {
        super(FileHandler.getUi().get("BORDER_M"));
        toSkill = s;
        this.index = index;
        sc = screen;
        skill = s.owner.deck.get(index);
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
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    public void onClick() {
        skill.owner.gainSkill(index, toSkill);
        Labyrintale.removeTempScreen(sc);
    }
}
