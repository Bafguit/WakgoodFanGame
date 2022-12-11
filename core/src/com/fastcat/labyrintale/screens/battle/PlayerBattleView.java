package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class PlayerBattleView extends BattleView {

    public boolean isOnLock = false;

    public PlayerBattleView(AbstractPlayer cls) {
        super(FileHandler.getUi().get("ENTITY_POINT"));
        entity = cls;
        entity.block = 0;
        showImg = false;
    }

    @Override
    protected void updateButton() {
        AbstractEntity t = battleScreen.currentTurnEntity();
        isOnLock = t != null && t == entity;
        if (battleScreen.isSelecting) {
            clickable = entity.isAlive() && isTarget;
            showImg = isLooking || isOnLock || (over && isTarget);
            if (over && clickable) {
                battleScreen.looking.add(entity);
                isLooking = true;
            }
        } else {
            showImg = isLooking || isOnLock;
            clickable = entity.isAlive();
        }
        mute = isOnLock;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && entity != null) {
            sb.setColor(Color.WHITE);
            entity.render(sb);
        }
    }

    public void renderLook(SpriteBatch sb) {
        if (enabled && entity != null && !entity.isDead && showImg && battleScreen.cType == ControlPanel.ControlType.BATTLE) {
            sb.setColor(Color.WHITE);
            sb.draw(battleScreen.currentTurnEntity() == entity ? turnLook.img : targetLook.img,
                    entity.animX - targetLook.sWidth / 2,
                    entity.animY - targetLook.sHeight * 0.6f,
                    targetLook.sWidth,
                    targetLook.sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (entity != null && entity.isAlive()) {
            if (battleScreen.isSelecting) battleScreen.gets.onTargetSelected(entity);
        }
    }
}
