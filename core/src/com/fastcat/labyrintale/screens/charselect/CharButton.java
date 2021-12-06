package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass.*;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class CharButton extends AbstractUI {

    private Sprite border = CHAR_SELECT;
    private Sprite bg;
    public boolean showBg = false;
    private boolean isCharSt = false;
    public boolean isOnLock = false;
    private boolean isChar = true;

    public AbstractPlayer.PlayerClass selected;
    public CharButton sChar;

    public CharButton() {
        this(TEST);
        showImg = false;
        isChar = false;
    }

    public CharButton(AbstractPlayer.PlayerClass cls) {
        super(getWak(cls));
        selected = cls;
    }

    private static Sprite getWak(AbstractPlayer.PlayerClass cls) {
        switch (cls) {
            case BABY:
                return WAK_BABY;
            case SAJANG:
                return WAK_SAJANG;
            case BASIC:
                return WAK_BASIC;
            default:
                return CHAR_SELECT;
        }
    }

    private static Sprite getWakBg(AbstractPlayer.PlayerClass cls) {
        switch (cls) {
            case BABY:
                return WAK_BABY_BG;
            case SAJANG:
                return WAK_SAJANG_BG;
            default:
                return WAK_BASIC_BG;
        }
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.LIGHT_GRAY);
            if(!isChar && showBg) sb.draw(bg, x + sWidth / 2 - Gdx.graphics.getWidth() * 0.125f, 0, bg.getWidth() * scale * uiScale, bg.getHeight() * scale * uiScale);
            if(isCharSt) sb.setColor(Color.DARK_GRAY);
            else if (over) sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);

            if(fontData != null) {
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(isChar) {
            if(!isCharSt) {
                for(int i = 0; i < charSelectScreen.chars.length; i++) {
                    CharButton chb = charSelectScreen.chars[i];
                    if(!chb.isOnLock) {
                        chb.isOnLock = true;
                        chb.selected = selected;
                        chb.sChar = this;
                        chb.showImg = true;
                        chb.img = getWak(selected);
                        chb.bg = getWakBg(selected);
                        chb.showBg = true;
                        isCharSt = true;
                        break;
                    }
                }
            } else {
                for(int i = 0; i < charSelectScreen.chars.length; i++) {
                    CharButton chb = charSelectScreen.chars[i];
                    if(chb.sChar == this) {
                        chb.removeChar();
                    }
                }
            }
        } else if (isOnLock){
            removeChar();
        }
    }

    public void removeChar() {
        selected = TEST;
        showImg = false;
        isOnLock = false;
        showBg = false;
        if(sChar != null) {
            sChar.isCharSt = false;
            sChar = null;
        }
    }
}
