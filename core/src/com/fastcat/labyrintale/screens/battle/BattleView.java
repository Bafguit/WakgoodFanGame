package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractUI;

public class BattleView extends AbstractUI {

    protected Sprite pImg;
    public AbstractEntity entity;
    public boolean isLooking = false;
    public boolean isTarget = false;

    public BattleView(Sprite s) {
        super(s);
    }
}
