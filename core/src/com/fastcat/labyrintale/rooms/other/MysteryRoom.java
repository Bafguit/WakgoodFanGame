package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.handlers.GroupHandler;

import java.util.Objects;

public class MysteryRoom extends AbstractRoom {

    private static final String ID = "Mystery";

    public MysteryRoom() {
        super(ID, RoomType.MYSTERY);
    }
}
