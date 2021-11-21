package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.advisorSelectScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.ImageHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class MapNodeButton extends AbstractUI {

    private Texture border = CHAR_SELECT;
    public boolean canGo = true;
    public AbstractRoom room;

    public MapNodeButton(AbstractRoom room) {
        super(getWak(room.type));
        this.room = room;
    }

    private static Texture getWak(AbstractRoom.RoomType type) {
        switch (type) {
            case BOSS:
                return WAK_BABY;
            case ELITE:
                return WAK_SAJANG;
            case BATTLE:
                return WAK_BASIC;
            default:
                return CHAR_SELECT;
        }
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!room.isDone) {
                if (!over || !canGo) sb.setColor(Color.LIGHT_GRAY);
            } else sb.setColor(Color.DARK_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void onClick() {
        if(!room.isDone && canGo) {
            room.done();
            if(currentFloor.roomNum == 4) currentFloor.canBoss = true;
            else currentFloor.roomNum++;
        }
    }
}
