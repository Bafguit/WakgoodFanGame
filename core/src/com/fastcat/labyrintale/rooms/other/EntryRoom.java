package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.events.StartEvent;

public class EntryRoom extends AbstractRoom {
    public EntryRoom() {
        super(new StartEvent());
    }
}
