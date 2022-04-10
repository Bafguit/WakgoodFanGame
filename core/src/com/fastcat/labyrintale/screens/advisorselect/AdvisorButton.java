package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.advisorSelectScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class AdvisorButton extends AbstractUI {

    private Sprite ai;
    private Sprite bg;
    public boolean showBg = false;
    private boolean isCharSt = false;
    public boolean isOnLock = false;
    private boolean isChar = true;

    public AbstractAdvisor.AdvisorClass selected;
    public AdvisorButton sChar;

    public AdvisorButton() {
        super(BORDER);
        showImg = false;
        isChar = false;
    }

    public AdvisorButton(AbstractAdvisor.AdvisorClass cls) {
        super(BORDER);
        ai = advImg.get(cls);
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
            if(isChar) {
                if(showImg) sb.draw(ai, x, y, sWidth, sHeight);
                sb.draw(img, x, y, sWidth, sHeight);
            }
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
            AdvisorButton chb = advisorSelectScreen.advisor;
            if(!isCharSt) {
                if(chb.isOnLock) {
                    chb.removeChar();
                }
                chb.isOnLock = true;
                chb.selected = selected;
                chb.sChar = this;
                chb.showImg = true;
                chb.ai = advImg.get(selected);
                chb.bg = advBgImg.get(selected);
                chb.showBg = true;
                isCharSt = true;
            } else if(chb.sChar == this) {
                chb.removeChar();
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
