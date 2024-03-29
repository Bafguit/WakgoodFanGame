package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;

public class RestButton extends AbstractUI implements GetSelectedPlayer, GetSelectedSlot, GetRewardDone {

    public static final int HEAL_AMOUNT = 8;

    public final float aa = Color.LIGHT_GRAY.r;
    public float a = aa;
    public RestScreen sc;
    public RestType type;

    public RestButton(RestScreen s, RestType t) {
        super(FileHandler.getUi().get("REST_" + t.toString()));
        sc = s;
        type = t;
    }

    @Override
    protected void updateButton() {
        if (over) {
            if (type == RestType.HEAL) {
                AbstractLabyrinth.cPanel.infoPanel.setInfo("휴식", "모든 플레이어가 체력을 &g<" + HEAL_AMOUNT + "> 회복합니다.");
            } else if (type == RestType.UPGRADE) {
                AbstractLabyrinth.cPanel.infoPanel.setInfo("단련", "스킬 하나를 강화합니다.");
            } else if (type == RestType.REVIVE) {
                AbstractLabyrinth.cPanel.infoPanel.setInfo("소생", "사망한 플레이어 하나를 부활시킵니다.");
            }
        }
    }

    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) {
                a -= Labyrintale.tick * 2;
                if (a <= aa) a = aa;
            } else {
                a += Labyrintale.tick * 2;
                if (a >= 1) a = 1;
            }
            sb.setColor(a, a, a, 1);

            if (showImg) sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    public void onClick() {
        if (type == RestType.HEAL) {
            SoundHandler.playSfx("HEAL");
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                p.heal(HEAL_AMOUNT);
            }
            sc.finishRest();
        } else if (type == RestType.UPGRADE) {
            Labyrintale.addTempScreen(new SlotSelectScreen(this, this));
        } else if (type == RestType.REVIVE) {
            Array<AbstractPlayer> temp = new Array<>();
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (!p.dummy && !p.isAlive()) temp.add(p);
            }
            AbstractPlayer[] tp = new AbstractPlayer[temp.size];
            for (int i = 0; i < temp.size; i++) {
                tp[i] = temp.get(i);
            }
            Labyrintale.addTempScreen(new PlayerSelectScreen(tp, this, this));
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        if (type == RestType.REVIVE) {
            player.revive();
            sc.finishRest();
        } else {
            sc.finishRest();
        }
    }

    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        sc.finishRest();
    }

    @Override
    public void isRewardDone(boolean isDone) {}

    public enum RestType {
        HEAL,
        UPGRADE,
        REVIVE
    }
}
