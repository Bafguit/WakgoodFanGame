package com.fastcat.labyrintale.screens.way;

import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class WaySelectButton extends AbstractUI {

    public WayScreen screen;
    public AbstractRoom room;

    public WaySelectButton(WayScreen s, WayIcon i, WayDesc d, AbstractRoom r) {
        super(FileHandler.CHAR_SKILL_REWARD);
        screen = s;
        room = r;
    }

    @Override
    public void onClick() {
        if(currentFloor.num == 11) {
            currentFloor.canBoss = true;
        } else {
            currentFloor.num++;
        }
        currentFloor.currentRoom = room;
        if (room.type == AbstractRoom.RoomType.BATTLE || room.type == AbstractRoom.RoomType.ELITE || room.type == AbstractRoom.RoomType.BOSS) {
            battleScreen = new BattleScreen();
            fadeOutAndChangeScreen(battleScreen);
        }
    }
}
