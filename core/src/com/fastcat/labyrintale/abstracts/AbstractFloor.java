package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class AbstractFloor {
    public Array<AbstractEnemy> enemyList;

    public AbstractRoom[] upNodes = new AbstractRoom[5];
    public AbstractRoom[] downNodes = new AbstractRoom[5];
    public AbstractRoom bossRoom;
    public AbstractRoom currentRoom;
    public int floorNum;
    public int roomNum;

    public AbstractFloor() {
        this(1);
    }

    public AbstractFloor(int f) {
        this.floorNum = f;
        this.roomNum = 0;
    }
}
