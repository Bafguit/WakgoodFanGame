package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;

import java.io.Serializable;

public class AbstractWay {
    public Sprite img = FileHandler.charImg.get(AbstractPlayer.PlayerClass.BURGER);
    public WayType type;
    public AbstractChoice[] choices;
    public boolean isDone = false;

    public AbstractWay(SaveHandler.WayData data) {
        type = WayType.valueOf(data.type);
        int l = data.choices.length;
        choices = new AbstractChoice[l];
        for(int i = 0; i < l; i++) {
            choices[i] = new AbstractChoice(data.choices[i]);
        }
        isDone = data.isDone;
    }

    public AbstractWay(AbstractChoice c, WayType t) {
        choices = new AbstractChoice[1];
        choices[0] = c;
        type = t;
    }

    public AbstractWay(Array<AbstractChoice> s, WayType t) {
        choices = s.toArray(AbstractChoice.class);
        type = t;
    }

    public void done() {
        isDone = true;
    }

    public enum WayType {
        ENTRY, REST, SHOP, WEAK, NORMAL, ELITE, BOSS
    }
}
