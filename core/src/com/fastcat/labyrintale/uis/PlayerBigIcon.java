package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.charInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.HP_N;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;

public class PlayerBigIcon extends AbstractUI {
    private static final FontHandler.FontData fontHp = HP;

    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public AbstractEntity p;
    public Sprite hb;
    public float hx, hy, hw, hh;

    public PlayerBigIcon(AbstractPlayer p) {
        super(FileHandler.getUi().get("BORDER_V"));
        this.p = p;
        hb = FileHandler.getUi().get("HEALTH_BAR");
        hx = 22 * scale;
        hy = 21 * scale;
        hw = 162 * scale;
        hh = 26 * scale;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && p != null) {
            sb.setColor(Color.WHITE);
            sb.draw(p.imgPanel, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            float sc = ((float) p.health) / ((float) p.maxHealth);
            sb.draw(hbb, x + hx, y + hy, hw, hh);
            sb.draw(hb, x + hx, y + hy, 0, 0, hw, hh, sc, 1, 0);
            FontHandler.renderCenter(
                    sb,
                    p.isNeut || !p.isAlive() ? HP_N : FontHandler.HP,
                    p.health + "/" + p.maxHealth,
                    x,
                    y + hy + 14 * scale,
                    sWidth,
                    y);
        }
    }

    @Override
    protected void updateButton() {
        if (over && p != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(p);
        }
    }

    public void setPlayer(AbstractEntity p) {
        this.p = p;
        for (int i = 0; i < 8; i++) {
            AbstractLabyrinth.cPanel.battlePanel.stats[i].entity = AbstractLabyrinth.cPanel.battlePanel.curPlayer;
            AbstractLabyrinth.cPanel.battlePanel.stats[i].update();
        }
    }

    @Override
    protected void onClick() {
        if (p.isPlayer) {
            AbstractPlayer player = (AbstractPlayer) p;
            if (charInfoScreen == null) {
                charInfoScreen = new CharInfoScreen(player);
                Labyrintale.addTempScreen(charInfoScreen);
            } else if (charInfoScreen.player == player) {
                if (Labyrintale.getCurScreen() != charInfoScreen) {
                    Labyrintale.removeTempScreen(charInfoScreen);
                    Labyrintale.addTempScreen(charInfoScreen);
                } else {
                    Labyrintale.removeTempScreen(charInfoScreen);
                    charInfoScreen = null;
                }
            } else {
                if (Labyrintale.getCurScreen() != charInfoScreen) {
                    Labyrintale.removeTempScreen(charInfoScreen);
                    charInfoScreen.setPlayer(player);
                    Labyrintale.addTempScreen(charInfoScreen);
                } else charInfoScreen.setPlayer(player);
            }
        }
    }
}
