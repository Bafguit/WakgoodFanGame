package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.events.FloorSecondEvent;
import com.fastcat.labyrintale.events.StartEvent;

public class SecondFloorRoom extends AbstractRoom {
    public SecondFloorRoom() {
        super(new FloorSecondEvent());
    }
}
