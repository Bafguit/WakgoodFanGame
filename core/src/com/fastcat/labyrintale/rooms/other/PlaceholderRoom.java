package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractRoom;

public class PlaceholderRoom extends AbstractRoom {
    public PlaceholderRoom() {
        super("Placeholder", RoomType.ENTRY);
        isDone = true;
        battleDone = true;
    }
}
