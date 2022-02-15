package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.common.Test;
import com.fastcat.labyrintale.rooms.other.Placeholder;

import static com.fastcat.labyrintale.abstracts.AbstractFloor.NodeType.*;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public class AbstractFloor {
    public Array<AbstractEnemy> enemyList;

    public AbstractRoom entryRoom;
    public AbstractRoom[] upRooms = new AbstractRoom[5];
    public AbstractRoom[] downRooms = new AbstractRoom[5];
    public AbstractRoom[] rooms = new AbstractRoom[12];
    public AbstractRoom bossRoom;
    public AbstractRoom currentRoom;
    public AbstractWay way;
    public boolean canBoss;
    public int floorNum;
    public int roomNum;
    public NodeType nt;

    public AbstractFloor() {
        this(1);
    }

    public AbstractFloor(int f) {
        this.floorNum = f;
        this.canBoss = false;
        this.roomNum = 0;
        entryRoom = new Placeholder();
        entryRoom.done();
        currentRoom = entryRoom;
        for(int i = 0; i < 5; i++) {
            upRooms[i] = new Test();
            downRooms[i] = new Test();
        }
        for(int i = 0; i < 12; i++) {
            rooms[i] = new Test();
        }
        bossRoom = new TestBoss();
        setDirection(NONE);
    }

    public void setDirection(NodeType dir) {
        if(dir == UP) {
            for(int i = 0; i < 5; i++) {
                downRooms[i].done();
            }
        } else if(dir == DOWN) {
            for(int i = 0; i < 5; i++) {
                upRooms[i].done();
            }
        }
        nt = dir;
    }

    public enum NodeType {
        NONE, UP, DOWN
    }
}
