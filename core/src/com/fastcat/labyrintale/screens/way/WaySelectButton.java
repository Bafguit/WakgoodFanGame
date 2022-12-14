package com.fastcat.labyrintale.screens.way;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class WaySelectButton extends AbstractUI {

    public WayScreen screen;
    public AbstractChoice c;

    public WaySelectButton(WayScreen s, AbstractChoice r) {
        super(FileHandler.getUi().get("WAY_SELECT"));
        screen = s;
        c = r;
    }

    @Override
    protected void updateButton() {
        overable = !Labyrintale.wayScreen.isSelecting;
    }

    @Override
    public void onClick() {
        currentFloor.currentRoom = c.room;
        currentFloor.currentWay.selected = c.index;
        SaveHandler.save();
        currentFloor.currentRoom.enter();
    }
}
