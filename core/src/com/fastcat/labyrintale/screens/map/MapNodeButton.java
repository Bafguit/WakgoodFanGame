package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.abstracts.AbstractWay;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.way.WayScreen;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.abstracts.AbstractRoom.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class MapNodeButton extends AbstractUI {

    private final Sprite border = FileHandler.ui.get("BORDER");
    public boolean canGo = true;
    public AbstractWay way;

    public MapNodeButton(AbstractWay w) {
        super(w.img);
        this.way = w;
        setScale(0.75f);
    }

    @Override
    protected void updateButton() {
        if(over) {
            cPanel.infoPanel.setInfo(way.name, way.desc);
        }
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(!way.isDone) {
                if(canGo) {
                    if (over) {
                        sb.setColor(WHITE);
                    } else {
                        if (!mapScreen.isView) sb.setColor(mapScreen.alpha, mapScreen.alpha, mapScreen.alpha, 1.0f);
                        else sb.setColor(Color.LIGHT_GRAY);
                    }
                } else sb.setColor(Color.LIGHT_GRAY);
            } else sb.setColor(Color.DARK_GRAY);
            //if(currentFloor.currentWay == way && !way.isDone) sb.setColor(WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(WHITE);
        }
    }

    @Override
    protected void onClick() {
        if(!way.isDone && canGo && !mapScreen.isView) {
            currentFloor.currentRoom = currentFloor.rooms[currentFloor.num];
            currentFloor.currentWay = way;
            addTempScreen(new WayScreen());
        }
    }
}
