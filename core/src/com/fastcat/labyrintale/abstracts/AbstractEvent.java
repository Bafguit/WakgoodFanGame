package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.EventString;

import java.util.Objects;

public abstract class AbstractEvent {

    protected final EventString.EventData data;
    public final String id;
    public Sprite img;
    public String name;
    public String desc = "";
    public EventChoice[] choices;

    public AbstractEvent(String id, int size) {
        this.id = id;
        data = StringHandler.eventString.data.get(this.id);
        name = data.NAME;
        choices = new EventChoice[size];
    }

    public static abstract class EventChoice {
        public String text;

        public EventChoice(String text) {
            this.text = text;
        }

        public abstract void onSelect();

        public boolean available() {
            return true;
        }
    }

    protected Sprite getImage(int index) {
        return FileHandler.eventImg.get(data.IMAGE[index]);
    }
}
