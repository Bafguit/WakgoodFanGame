package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.CharString;

import static com.fastcat.labyrintale.Labyrintale.charSelectScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class CharButton extends AbstractUI {

    private Sprite ci;
    private Sprite bg;
    public CharString.CharData charData;
    public boolean showBg = false;
    private boolean isCharSt = false;
    public boolean isOnLock = false;
    private boolean isChar = true;

    public AbstractPlayer.PlayerClass selected;
    public CharButton sChar;

    public CharButton() {
        super(FileHandler.ui.get("BORDER"));
        showImg = false;
        isChar = false;
    }

    public CharButton(AbstractPlayer.PlayerClass cls) {
        super(FileHandler.ui.get("BORDER"));
        ci = charImg.get(cls);
        selected = cls;
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
            if(showImg) sb.draw(ci, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
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
                        chb.ci = charImg.get(selected);
                        chb.bg = charBgImg.get(selected);
                        chb.showBg = true;
                        chb.charData = StringHandler.charString.get(selected.toString().toLowerCase());
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
        selected = null;
        showImg = false;
        isOnLock = false;
        showBg = false;
        if(sChar != null) {
            sChar.isCharSt = false;
            sChar = null;
        }
    }
}
