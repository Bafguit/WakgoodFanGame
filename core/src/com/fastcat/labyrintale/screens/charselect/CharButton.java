package com.fastcat.labyrintale.screens.charselect;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CharButton extends AbstractUI {

    public AbstractPlayer player;
    public boolean isCharSt = false;
    public boolean isOnLock = false;
    public boolean isChar = true;
    public CharButton sChar;
    public CharSelectScreen sc;

    public CharButton(CharSelectScreen sc) {
        super(FileHandler.getUi().get("BORDER_CHAR"));
        this.sc = sc;
        showImg = false;
        clickable = false;
        isChar = false;
        isPixmap = true;
    }

    public CharButton(CharSelectScreen sc, AbstractPlayer player) {
        super(FileHandler.getUi().get("BORDER_CHAR"));
        this.sc = sc;
        this.player = player;
        isPixmap = true;
    }

    @Override
    protected void updateButton() {
        if (over && player != null && charSelectScreen.selected != player) {
            charSelectScreen.group.setPlayer(player);
            charSelectScreen.selected = player;
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && !(sc.mode == AbstractLabyrinth.Mode.SOLO && isOnLock && player == null)) {
            sb.setColor(Color.WHITE);
            if (isCharSt) sb.setColor(Color.DARK_GRAY);
            else if (over && player != null) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            if (showImg) sb.draw(player.select, x, y, sWidth, sHeight);
            else sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (isChar) {
            if (!isCharSt) {
                CharButton cc = charSelectScreen.chars[0];
                if(sc.mode == AbstractLabyrinth.Mode.DUP && !cc.isOnLock) {
                    cc.player = player;
                    cc.isOnLock = true;
                    cc.sChar = this;
                    cc.showImg = true;
                    cc.clickable = true;
                    isCharSt = true;
                    sChar = cc;
                    for (int i = 1; i < charSelectScreen.chars.length; i++) {
                        CharButton chb = charSelectScreen.chars[i];
                        chb.player = player;
                        chb.sChar = this;
                        chb.showImg = true;
                        chb.clickable = true;
                    }
                } else {
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
                }
            } else {
                if (sChar != null) {
                    sChar.removeChar();
                    sChar = null;
                    if(sc.mode == AbstractLabyrinth.Mode.DUP) {
                        for (int i = 1; i < charSelectScreen.chars.length; i++) {
                            CharButton chb = charSelectScreen.chars[i];
                            chb.removeChar();
                            chb.isOnLock = true;
                        }
                    }
                }
            }
        } else {
            if(sc.mode == AbstractLabyrinth.Mode.DUP) {
                charSelectScreen.chars[0].removeChar();
                for (int i = 1; i < charSelectScreen.chars.length; i++) {
                    CharButton chb = charSelectScreen.chars[i];
                    chb.removeChar();
                    chb.isOnLock = true;
                }
            } else {
                removeChar();
            }
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
