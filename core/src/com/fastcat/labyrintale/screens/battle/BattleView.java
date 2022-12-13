package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public abstract class BattleView extends AbstractUI {

    protected TempUI turnLook;
    protected TempUI targetLook;
    public AbstractEntity entity;
    public boolean isLooking = false;
    public boolean isTarget = false;
    public boolean fade = false;
    public float alpha = 0;
    public int statSize = 4;

    public BattleView(Sprite s) {
        super(s);
        turnLook = new TempUI(FileHandler.getUi().get("POINT_TURN"));
        targetLook = new TempUI(FileHandler.getUi().get("POINT_ALLEY"));
        turnLook.img.setAlpha(0);
        targetLook.img.setAlpha(0);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        turnLook.img.setBounds(x, y, turnLook.sWidth, turnLook.sHeight);
        targetLook.img.setBounds(x, y, targetLook.sWidth, targetLook.sHeight);
    }

    public void renderLook(SpriteBatch sb) {
        fade = enabled
                && entity != null
                && !entity.isDead
                && showImg
                && battleScreen.cType == ControlPanel.ControlType.BATTLE;

        if (fade) {
            alpha += Labyrintale.tick * 8;
            if (alpha >= 1) alpha = 1;
        } else {
            alpha -= Labyrintale.tick * 8;
            if (alpha <= 0) alpha = 0;
        }

        sb.setColor(Color.WHITE);
        if (battleScreen.currentTurnEntity() == entity) {
            turnLook.img.setAlpha(alpha);
            turnLook.img.setPosition(entity.animX - turnLook.sWidth / 2, entity.animY - turnLook.sHeight * 0.6f);
            turnLook.img.draw(sb);
        } else {
            targetLook.img.setAlpha(alpha);
            targetLook.img.setPosition(entity.animX - targetLook.sWidth / 2, entity.animY - targetLook.sHeight * 0.6f);
            targetLook.img.draw(sb);
        }
    }
}
