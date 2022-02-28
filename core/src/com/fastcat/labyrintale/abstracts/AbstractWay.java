package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class AbstractWay {
    public Sprite img;
    public String name;
    public String desc;
    public WayType type;
    public AbstractChoice[] choices;
    public boolean isDone = false;

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
        ENTRY, REST, NORMAL, ELITE, BOSS
    }
}
