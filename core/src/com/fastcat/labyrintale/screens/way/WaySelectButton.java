package com.fastcat.labyrintale.screens.way;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;

public class WaySelectButton extends AbstractUI {

    private final float aa = Color.LIGHT_GRAY.r;
    public AbstractLabyrinth.Difficulty diff;
    public float a = aa;

    private final Sprite door;

    public WayScreen screen;
    public AbstractChoice c;

    public WaySelectButton(WayScreen s, AbstractChoice r, int num) {
        super(FileHandler.getUi().get("WAY_SELECT"));
        door = FileHandler.getUi().get("WAY_" + num);
        screen = s;
        c = r;
    }

    @Override
    protected void updateButton() {
        overable = !Labyrintale.wayScreen.isSelecting;
    }

    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && over) {
                a += Labyrintale.tick * 3;
                if (a >= 1) a = 1;
            } else {
                a -= Labyrintale.tick * 3;
                if (a <= aa) a = aa;
            }
            sb.setColor(a, a, a, 1);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(door, x, y, sWidth, sHeight);
        }
    }

    @Override
    public void show() {
        a = aa;
    }

    @Override
    public void onClick() {
        currentFloor.currentRoom = c.room;
        currentFloor.currentWay.selected = c.index;
        SaveHandler.save();
        currentFloor.currentRoom.enter();
    }
}
