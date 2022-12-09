package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.charInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.HP_N;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;

public class PlayerIcon extends AbstractUI {
    private static final FontHandler.FontData fontHp = FontHandler.HP;

    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public int index;
    public AbstractPlayer p;
    public Sprite hb;
    public float hx, hy, hw, hh;

    public PlayerIcon(int index) {
        super(FileHandler.getUi().get("BORDER_V"));
        this.index = index;
        p = AbstractLabyrinth.players[index];
        hb = FileHandler.getUi().get("HEALTH_BAR");
        hx = 22 * scale;
        hy = 21 * scale;
        hw = 162 * scale;
        hh = 26 * scale;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && p != null) {
            sb.setColor(p.isAlive() ? p.animColor : Color.DARK_GRAY);
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
        p = AbstractLabyrinth.players[index];
        if (over && p != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(p);
        }
    }

    @Override
    protected void onOver() {}

    @Override
    protected void onClick() {
        if (charInfoScreen == null) {
            charInfoScreen = new CharInfoScreen(p);
            Labyrintale.addTempScreen(charInfoScreen);
        } else if (charInfoScreen.player == p) {
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
                charInfoScreen.setPlayer(p);
                Labyrintale.addTempScreen(charInfoScreen);
            } else charInfoScreen.setPlayer(p);
        }
    }
}
