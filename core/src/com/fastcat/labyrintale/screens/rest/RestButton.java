package com.fastcat.labyrintale.screens.rest;

import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class RestButton extends AbstractUI {

    public RestScreen screen;
    public AbstractChoice c;

    public RestButton(RestScreen s, AbstractChoice r) {
        super(FileHandler.CHAR_SKILL_REWARD);
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
        }
    }
}
