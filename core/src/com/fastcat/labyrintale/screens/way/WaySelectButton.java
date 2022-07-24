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
        currentFloor.num++;
        currentFloor.currentRoom = c.room;
        SaveHandler.save();
        currentFloor.currentRoom.enter();
    }
}
