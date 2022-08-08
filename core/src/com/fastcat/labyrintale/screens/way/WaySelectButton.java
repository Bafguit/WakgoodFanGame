package com.fastcat.labyrintale.screens.way;

import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class WaySelectButton extends AbstractUI {

    public WayScreen screen;
    public AbstractChoice c;

    public WaySelectButton(WayScreen s, AbstractChoice r) {
        super(FileHandler.getUi().get("WAY_SELECT"));
        screen = s;
        c = r;
    }

    @Override
    public void onClick() {
        currentFloor.currentRoom = c.room;
        currentFloor.currentWay.selected = c.index;
        SaveHandler.save();
        currentFloor.currentRoom.enter();
    }
}
