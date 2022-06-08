package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.handlers.GroupHandler;

import java.util.Objects;

public class MysteryRoom extends AbstractRoom {

    private static final String ID = "Mystery";

    public MysteryRoom() {
        id = ID;
    }

    @Override
    public void entry() {
        int b = 10, s = 10, r = 10, e = 70;
        if(AbstractLabyrinth.bleak >= 20) {
            b = 35;
            if(AbstractLabyrinth.bleak >= 40) {
                r = 0;
            }
        }
        s = s + b;
        r = r + s;
        e = e + r;
        int x = AbstractLabyrinth.mapRandom.random(e);
        AbstractRoom temp;
        if(x < b) {
            //TODO 랜덤으로 변경
            temp = Objects.requireNonNull(GroupHandler.RoomGroup.normalGroup.get(AbstractLabyrinth.currentFloor.floorNum).get(0).clone());
            enemies = temp.enemies;
        } else if (x < s) {
            temp = new ShopRoom();
        } else if (x < r) {
            temp = new RestRoom();
        } else {
            temp = GroupHandler.RoomGroup.getNextEvent();
            event = temp.event;
        }
        id = temp.id;
        type = temp.type;
        done();
        battleDone = true;
        temp.entry();
        AbstractLabyrinth.currentFloor.currentRoom = temp;
    }
}
