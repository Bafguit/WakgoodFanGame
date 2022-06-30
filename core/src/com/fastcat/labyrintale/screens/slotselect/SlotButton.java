package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

public class SlotButton extends AbstractUI {

    public Sprite image;
    public AbstractPlayer player;
    public int index;
    public SlotSelectScreen select;

    public SlotButton(AbstractPlayer player, int index, SlotSelectScreen select) {
        super(FileHandler.ui.get("SLOT_UP"));
        this.player = player;
        this.index = index;
        clickable = this.player.slot[this.index] < 3;
        this.select = select;
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!clickable) sb.setColor(Color.DARK_GRAY);
            else if (select.selected == this || over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
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
