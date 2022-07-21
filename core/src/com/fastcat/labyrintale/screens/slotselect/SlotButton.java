package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

public class SlotButton extends AbstractUI {

    public Sprite image;
    public AbstractPlayer player;
    public int index;
    public int upCount;
    public SlotSelectScreen select;

    public SlotButton(AbstractPlayer player, int index, SlotSelectScreen select) {
        super(FileHandler.ui.get("SLOT_UP"));
        this.player = player;
        this.index = index;
        upCount = this.player.slot[this.index];
        clickable = upCount < 3;
        this.select = select;
        fontData = FontHandler.COOLDOWN;
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!clickable) sb.setColor(Color.DARK_GRAY);
            else if (select.selected == this || over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(player.deck.get(index).img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            if(upCount > 0) FontHandler.renderCenter(sb, fontData, "+" + upCount, x, y + sHeight / 2, sWidth, sHeight);
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        select.selected = this;
    }
}
