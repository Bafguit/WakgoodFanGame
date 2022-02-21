package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.abstracts.AbstractWay;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.way.WayScreen;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class MapNodeButton extends AbstractUI {

    private final Sprite border = BORDER;
    public boolean canGo = true;
    public AbstractRoom room;

    public MapNodeButton(AbstractRoom room) {
        super(getWak(room.type));
        this.room = room;
    }

    private static Sprite getWak(RoomType type) {
        return BURGER; //TODO 나중에 수정
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!room.isDone) {
                if (!over && canGo) {
                    sb.setColor(mapScreen.alpha, mapScreen.alpha, mapScreen.alpha, mapScreen.alpha);
                }
                else if (!canGo || mapScreen.isView) sb.setColor(Color.LIGHT_GRAY);
            } else sb.setColor(Color.DARK_GRAY);
            if(currentFloor.currentRoom == room && !room.isDone) sb.setColor(WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(WHITE);
        }
    }

    @Override
    protected void onClick() {
        if(!room.isDone && canGo && !mapScreen.isView) {
            currentFloor.addNum();
            if(currentFloor.currentWay.type == AbstractWay.WayType.BOSS) {
                battleScreen = new BattleScreen();
                fadeOutAndChangeScreen(battleScreen);
            } else {
                addTempScreen(new WayScreen());
            }
        }
    }
}
