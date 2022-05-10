package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;

import static com.fastcat.labyrintale.Labyrintale.charInfoScreen;

public class PlayerBigIcon extends AbstractUI {

    public AbstractPlayer p;

    public PlayerBigIcon(AbstractPlayer p) {
        super(FileHandler.ui.get("BORDER_B"));
        this.p = p;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled && p != null) {
            sb.setColor(Color.WHITE);
            sb.draw(p.imgBig, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {
        if(over && p != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(p);
        }
    }

    public void setPlayer(AbstractPlayer p) {
        this.p = p;
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(charInfoScreen == null) {
            charInfoScreen = new CharInfoScreen(p);
            Labyrintale.addTempScreen(charInfoScreen);
        } else if(charInfoScreen.player == p) {
            if(Labyrintale.getCurScreen() != charInfoScreen) {
                Labyrintale.removeTempScreen(charInfoScreen);
                Labyrintale.addTempScreen(charInfoScreen);
            }
            else {
                Labyrintale.removeTempScreen(charInfoScreen);
                charInfoScreen = null;
            }
        } else {
            if(Labyrintale.getCurScreen() != charInfoScreen) {
                Labyrintale.removeTempScreen(charInfoScreen);
                charInfoScreen.setPlayer(p);
                Labyrintale.addTempScreen(charInfoScreen);
            } else charInfoScreen.setPlayer(p);
        }
    }
}
