package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.screens.map.MapNodeButton;

import java.util.ArrayList;

import static com.fastcat.labyrintale.abstracts.AbstractFloor.NodeType.*;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType.*;

public class AbstractFloor {
    public Array<AbstractEnemy> enemyList;

    public AbstractRoom entryRoom;
    public AbstractRoom[] upRooms = new AbstractRoom[5];
    public AbstractRoom[] downRooms = new AbstractRoom[5];
    public AbstractRoom bossRoom;
    public AbstractRoom currentRoom;
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
        entryRoom = new AbstractRoom();
        entryRoom.done();
        currentRoom = entryRoom;
        for(int i = 0; i < 5; i++) {
            upRooms[i] = new AbstractRoom(BATTLE);
            downRooms[i] = new AbstractRoom(ELITE);
        }
        bossRoom = new AbstractRoom(BOSS);
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
