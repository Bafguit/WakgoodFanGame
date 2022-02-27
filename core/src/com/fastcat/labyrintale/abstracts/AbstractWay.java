package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class AbstractWay {
    public Sprite img;
    public String name;
    public String desc;
    public WayType type;
    public AbstractRoom[] rooms;
    public boolean isDone = false;

    public AbstractWay(AbstractRoom r, WayType t) {
        rooms = new AbstractRoom[1];
        rooms[0] = r;
        type = t;
    }

    public AbstractWay(Array<AbstractRoom> s, WayType t) {
        rooms = s.toArray(AbstractRoom.class);
        type = t;
    }

    public void done() {
        isDone = true;
    }

    public enum WayType {
        ENTRY, REST, NORMAL, ELITE, BOSS
    }
}
