package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.common.Test;
import com.fastcat.labyrintale.rooms.other.Placeholder;

import static com.fastcat.labyrintale.abstracts.AbstractWay.WayType.*;

public class AbstractFloor {
    public Array<AbstractEnemy> enemyList;

    public AbstractRoom entryRoom;
    public AbstractRoom[] rooms = new AbstractRoom[13];
    public AbstractRoom bossRoom;
    public AbstractRoom currentRoom;
    public AbstractWay currentWay;
    public AbstractWay[] ways = new AbstractWay[13];
    public boolean canBoss;
    public int floorNum;
    public int num;

    public AbstractFloor() {
        this(1);
    }

    public AbstractFloor(int f) {
        this.floorNum = f;
        this.canBoss = false;
        this.num = 0;
        rooms[0] = entryRoom = new Test();
        entryRoom.done();
        for(int i = 1; i < 12; i++) {
            rooms[i] = new Test();
        }
        rooms[12] = bossRoom = new TestBoss();

        ways[0] = new AbstractWay(generateWay(ENTRY), ENTRY);
        for(int i = 1; i < 4; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[4] = new AbstractWay(generateWay(ELITE), ELITE);
        for(int i = 5; i < 8; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[8] = new AbstractWay(generateWay(ELITE), ELITE);
        for(int i = 9; i < 12; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[12] = new AbstractWay(generateWay(BOSS), BOSS);
    }

    private Array<AbstractRoom> generateWay(AbstractWay.WayType type) {
        Array<AbstractRoom> t = new Array<>();
        if(type == ENTRY) {
            //TODO 여기에 길 정보 입력
            //겁나힘들다 슈바...
        }
        return t;
    }

    public void addNum() {
        num++;
        currentWay = ways[num];
        currentRoom = rooms[num];
    }
}
