package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.screens.battle.BattleScreen;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class WaySelectButton extends AbstractUI {

    public WayScreen screen;
    public AbstractRoom room;

    public WaySelectButton(WayScreen s, AbstractRoom r, Sprite texture) {
        super(texture);
        screen = s;
        room = r;
    }

    @Override
    public void onClick() {
        if(currentFloor.roomNum == 4) {
            currentFloor.canBoss = true;
        } else {
            currentFloor.roomNum++;
        }
        currentFloor.currentRoom = room;
        if (room.type == AbstractRoom.RoomType.BATTLE || room.type == AbstractRoom.RoomType.ELITE || room.type == AbstractRoom.RoomType.BOSS) {
            battleScreen = new BattleScreen();
            fadeOutAndChangeScreen(battleScreen);
        }
    }
}
