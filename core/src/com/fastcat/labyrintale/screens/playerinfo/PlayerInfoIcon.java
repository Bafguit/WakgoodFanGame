package com.fastcat.labyrintale.screens.playerinfo;

import static com.fastcat.labyrintale.Labyrintale.charInfoScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;

public class PlayerInfoIcon extends AbstractUI {

    public int index;
    public AbstractPlayer p;

    public PlayerInfoIcon(int index) {
        super(FileHandler.getUi().get("BORDER_V2"));
        this.index = index;
        setScale(0.9f);
        p = AbstractLabyrinth.players[index];
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && p != null) {
            sb.setColor(p.isAlive() ? p.animColor : Color.DARK_GRAY);
            sb.draw(p.imgPanel, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
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
