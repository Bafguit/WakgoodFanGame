package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public abstract class BattleView extends AbstractUI {

    protected TempUI turnLook;
    protected TempUI targetLook;
    public AbstractEntity entity;
    public boolean isLooking = false;
    public boolean isTarget = false;
    public int statSize = 4;

    public BattleView(Sprite s) {
        super(s);
        turnLook = new TempUI(FileHandler.getUi().get("POINT_TURN"));
        targetLook = new TempUI(FileHandler.getUi().get("POINT_ALLEY"));
    }
}
