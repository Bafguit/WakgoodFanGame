package com.fastcat.labyrintale.screens.allskill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.slotselect.SlotButton;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;
import com.fastcat.labyrintale.uis.BgImg;

public class AllSkillScreen extends AbstractScreen implements GetSelectedSlot {

    public BgImg bg = new BgImg();
    public AllSkillText slotSelectText;
    public PlayerSlotIcon[] pIcons = new PlayerSlotIcon[4];
    public AllSlotButton[][] pPlayer;
    public GetSelectedSlot gets;
    public SlotType type;

    public AllSkillScreen(GetSelectedSlot gets) {
        this(gets, SlotType.UPGRADE);
    }

    public AllSkillScreen(GetSelectedSlot gets, SlotType type) {
        slotSelectText = new AllSkillText();
        this.gets = gets;
        this.type = type;
        pPlayer = new AllSlotButton[4][3];
        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        if(AbstractLabyrinth.mode == AbstractLabyrinth.Mode.SOLO) {
            AbstractPlayer pp = AbstractLabyrinth.players[0];
            float bw = w * 0.42f;
            PlayerSlotIcon cc = new PlayerSlotIcon(pp);
            cc.setPosition(bw - cc.sWidth / 2, h * 0.62f - cc.sHeight / 2);
            for (int i = 0; i < 3; i++) {
                AllSlotButton adv = new AllSlotButton(pp, i, this);
                adv.setPosition(bw + w * 0.08f * (i + 1) - adv.sWidth / 2, h * 0.62f - adv.sHeight / 2);
                pPlayer[0][i] = adv;
            }
            pIcons[0] = cc;
            for (int k = 1; k < 4; k++) {
                AbstractPlayer p = AbstractLabyrinth.players[k];
                PlayerSlotIcon c = new PlayerSlotIcon(p);
                c.setPosition(100000, 100000);
                for (int i = 0; i < 3; i++) {
                    AllSlotButton adv = new AllSlotButton(p, i, this);
                    adv.setPosition(10000, 10000);
                    pPlayer[k][i] = adv;
                }
                pIcons[k] = c;
            }
        } else {
            int cnt = 0;
            for (int l = 0; l < 2; l++) {
                for (int k = 0; k < 2; k++) {
                    AbstractPlayer p = AbstractLabyrinth.players[cnt];
                    float bw = w * (0.2f + 0.36f * k);
                    PlayerSlotIcon c = new PlayerSlotIcon(p);
                    c.setPosition(bw - c.sWidth / 2, h * (0.625f - 0.15f * l));
                    for (int i = 0; i < 3; i++) {
                        AllSlotButton adv = new AllSlotButton(p, i, this);
                        adv.setPosition(bw + w * 0.08f * (i + 1) - adv.sWidth / 2, h * (0.625f - 0.15f * l));
                        pPlayer[cnt][i] = adv;
                    }
                    pIcons[cnt++] = c;
                }
            }
        }
    }

    @Override
    public void update() {
        for (int i = 0; i < 4; i++) {
            pIcons[i].update();
            for (int j = 0; j < 3; j++) {
                pPlayer[i][j].update();
            }
        }
        slotSelectText.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (int i = 0; i < 4; i++) {
            pIcons[i].render(sb);
            for (int j = 0; j < 3; j++) {
                pPlayer[i][j].render(sb);
            }
        }
        slotSelectText.render(sb);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        for (int i = 0; i < 4; i++) {
            pIcons[i].dispose();
            for (int j = 0; j < 3; j++) {
                pPlayer[i][j].dispose();
            }
        }
    }

    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        gets.slotSelected(player, index);
    }

    public enum SlotType {
        UPGRADE
    }

    private static class PlayerSlotIcon extends AbstractUI {

        private final AbstractPlayer player;

        public PlayerSlotIcon(AbstractPlayer player) {
            super(FileHandler.getUi().get("BORDER_P"));
            this.player = player;
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            sb.setColor(Color.WHITE);
            if (player != null) sb.draw(player.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
