package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.EventString;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEvent implements Cloneable {

    protected final EventString.EventData data;
    public final String id;
    public Sprite img;
    public String name;
    public String desc = "";
    public int page = 0;
    public int size;
    public Array<EventChoice>[] choices;

    public AbstractEvent(String id, int size) {
        this.id = id;
        data = StringHandler.eventString.data.get(this.id);
        name = data.NAME;
        this.size = size;
        choices = new Array[this.size];
        for(int i = 0; i < this.size; i++) {
            choices[i] = getChoices(i);
        }
        //TODO 페이지 구현 필수
    }

    public void done() {

    }

    public void onChoose() {

    }

    public abstract Array<EventChoice> getChoices(int page);

    @Override
    public final AbstractEvent clone() {
        AbstractEvent clone = null;
        try {
            clone = (AbstractEvent) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public static abstract class EventChoice {
        public String text;
        public boolean isUsed = false;

        public EventChoice(String text) {
            this.text = text;
        }

        public final void select() {
            onSelect();
            isUsed = true;
        }

        protected abstract void onSelect();

        public final boolean available() {
            return !isUsed && condition();
        }

        protected boolean condition() {
            return true;
        }
    }

    protected Sprite getImage(int index) {
        return FileHandler.eventImg.get(data.IMAGE[index]);
    }
}
