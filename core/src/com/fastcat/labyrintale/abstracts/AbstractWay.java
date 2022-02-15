package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class AbstractWay {
    public Sprite img;
    public String name;
    public String desc;
    public AbstractRoom[] rooms;

    public AbstractWay(AbstractRoom r) {
        rooms = new AbstractRoom[1];
        rooms[0] = r;
    }

    public AbstractWay(int way, Array<AbstractRoom> s) {
        rooms = new AbstractRoom[way];
        for(int i = 0; i < way; i++) {
            if(i < s.size) {
                AbstractRoom temp = s.get(i);
                rooms[i] = temp;
            } else break;
        }
    }
}
