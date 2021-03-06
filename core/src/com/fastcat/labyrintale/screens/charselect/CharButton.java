package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;

public class CharButton extends AbstractUI {

    public AbstractPlayer player;
    public boolean isCharSt = false;
    public boolean isOnLock = false;
    public boolean isChar = true;
    public CharButton sChar;

    public CharButton() {
        super(FileHandler.getUi().get("BORDER_M"));
        showImg = false;
        clickable = false;
        isChar = false;
    }

    public CharButton(AbstractPlayer player) {
        super(FileHandler.getUi().get("BORDER_M"));
        this.player = player;
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            if (isCharSt) sb.setColor(Color.DARK_GRAY);
            else if (over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if (showImg) sb.draw(player.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void onOver() {
        if (player != null) {
            charSelectScreen.group.setPlayer(player);
            charSelectScreen.selected = player;
        }
    }

    @Override
    protected void onClick() {
        if (isChar) {
            if (!isCharSt) {
                for (int i = 0; i < charSelectScreen.chars.length; i++) {
                    CharButton chb = charSelectScreen.chars[i];
                    if (!chb.isOnLock) {
                        chb.player = player;
                        chb.isOnLock = true;
                        chb.sChar = this;
                        chb.showImg = true;
                        chb.clickable = true;
                        isCharSt = true;
                        sChar = chb;
                        break;
                    }
                }
            } else {
                if (sChar != null) {
                    sChar.removeChar();
                    sChar = null;
                }
            }
        } else {
            removeChar();
        }
    }

    public void removeChar() {
        player = null;
        showImg = false;
        isOnLock = false;
        clickable = false;
        if (sChar != null) {
            sChar.isCharSt = false;
            sChar = null;
        }
    }
}
