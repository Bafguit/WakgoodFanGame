package com.fastcat.labyrintale.screens.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.abstracts.AbstractWay;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.way.WayScreen;

import static com.badlogic.gdx.graphics.Color.LIGHT_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.addTempScreen;
import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class MapNodeButton extends AbstractUI {

    private final Sprite border = FileHandler.getUi().get("BORDER");
    public boolean canGo;
    public int wayIndex;
    public AbstractChoice choice;

    public MapNodeButton(AbstractChoice w, int index) {
        super(w.img);
        this.choice = w;
        canGo = w.type == AbstractChoice.ChoiceType.ENTRY;
        wayIndex = index;
    }

    @Override
    protected void updateButton() {
        if(over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(choice.name, choice.desc);
        }
    }

    public void render(SpriteBatch sb) {
        if (enabled) {
            if (!choice.room.isDone && canGo) {
                if(currentFloor.num == wayIndex) sb.setColor(mapScreen.alpha, mapScreen.alpha, mapScreen.alpha, 1.0f);
                else sb.setColor(LIGHT_GRAY);
            } else sb.setColor(Color.DARK_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(WHITE);
        }
    }

    //TODO 삭제 요망
    /*@Override
    protected void onClick() {
        if (!choice.room.isDone && canGo && !mapScreen.isView) {
            addTempScreen(new WayScreen());
        }
    }*/
}
