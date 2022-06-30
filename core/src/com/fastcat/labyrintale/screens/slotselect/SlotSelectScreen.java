package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.screens.playerselect.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerButton;
import com.fastcat.labyrintale.screens.playerselect.PlayerConfirmButton;
import com.fastcat.labyrintale.uis.BgImg;

public class SlotSelectScreen extends AbstractScreen implements GetSelectedSlot {

    public BgImg bg = new BgImg();
    public SlotSelectText slotSelectText;
    public SlotConfirmButton nextButton;
    public SlotButton selected;
    public SlotButton[] pPlayer;
    public GetSelectedSlot gets;
    public AbstractPlayer player;
    public SlotType type;

    public SlotSelectScreen(AbstractPlayer players, GetSelectedSlot gets) {
        this(players, gets, SlotType.UPGRADE);
    }

    public SlotSelectScreen(AbstractPlayer players, GetSelectedSlot gets, SlotType type) {
        slotSelectText = new SlotSelectText();
        nextButton = new SlotConfirmButton(this);
        this.gets = gets;
        this.player = players;
        this.type = type;
        pPlayer = new SlotButton[3];
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < 3; i++) {
            SlotButton adv = new SlotButton(this.player, i, this);
            adv.setPosition(w * (0.4f + 0.1f * i) - adv.sWidth / 2, h * 0.6f);
            pPlayer[i] = adv;
        }
    }

    @Override
    public void update() {
        for (SlotButton advisorButton : pPlayer) {
            advisorButton.update();
        }
        nextButton.update();
        slotSelectText.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (SlotButton advisorButton : pPlayer) {
            advisorButton.render(sb);
        }
        nextButton.render(sb);
        slotSelectText.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (SlotButton advisorButton : pPlayer) {
            advisorButton.dispose();
        }
    }

    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        if(type == SlotType.UPGRADE) {
            player.slot[index]++;
        }
        gets.slotSelected(player, index);
    }

    public enum SlotType {
        UPGRADE
    }
}
