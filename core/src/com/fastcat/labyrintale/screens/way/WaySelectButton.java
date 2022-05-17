package com.fastcat.labyrintale.screens.way;

import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.event.EventScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class WaySelectButton extends AbstractUI {

    public WayScreen screen;
    public AbstractChoice c;

    public WaySelectButton(WayScreen s, AbstractChoice r) {
        super(FileHandler.ui.get("WAY_SELECT"));
        screen = s;
        c = r;
    }

    @Override
    public void onClick() {
        if(currentFloor.num == 11) {
            currentFloor.canBoss = true;
        } else {
            currentFloor.num++;
        }
        currentFloor.currentRoom = c.room;
        currentFloor.currentRoom.enter();
        SaveHandler.save();
    }
}
