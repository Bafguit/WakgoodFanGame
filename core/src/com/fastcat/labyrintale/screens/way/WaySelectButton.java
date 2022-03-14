package com.fastcat.labyrintale.screens.way;

import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.rest.RestScreen;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class WaySelectButton extends AbstractUI {

    public WayScreen screen;
    public AbstractChoice c;

    public WaySelectButton(WayScreen s, AbstractChoice r) {
        super(FileHandler.WAY_SELECT);
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
        AbstractRoom r = c.room;
        currentFloor.currentRoom = r;
        if (r.type == AbstractRoom.RoomType.BATTLE || r.type == AbstractRoom.RoomType.ELITE || r.type == AbstractRoom.RoomType.BOSS) {
            battleScreen = new BattleScreen();
            fadeOutAndChangeScreen(battleScreen);
        } else if(r.type == AbstractRoom.RoomType.REST) {
            restScreen = new RestScreen();
            fadeOutAndChangeScreen(restScreen);
        }
    }
}
