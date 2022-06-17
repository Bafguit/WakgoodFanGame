package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class ShieldIcon extends AbstractUI {

    public AbstractStatus status;
    public AbstractEntity e;
    public int amount = 0;

    public ShieldIcon(AbstractEntity e) {
        super(FileHandler.ui.get("SHIELD"));
        fontData = BLOCK;
        overable = false;
        clickable = false;
        this.e = e;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(e.isAlive()) {
            amount = e.block;
            if (amount > 0) {
                sb.setColor(Color.WHITE);
                sb.draw(img, x, y, sWidth, sHeight);
                renderKeywordCenter(sb, fontData, Integer.toString(amount), x, y + sHeight * 0.5f, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void updateButton() {
    }
}
