package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.EventString;

public abstract class AbstractEvent implements Cloneable {

    public final String id;
    protected final EventString.EventData data;
    public Sprite img;
    public String name;
    public String desc = "";
    public int page;
    public int size;
    public boolean isEntry = false;
    public Array<EventChoice>[] choices;

    public AbstractEvent(String id, int size) {
        this.id = id;
        data = StringHandler.eventString.data.get(this.id);
        name = data.NAME;
        desc = getDescription(page);
        this.size = size;
        choices = new Array[this.size];
    }

    public final void generateChoices() {
        for (int i = 0; i < this.size; i++) {
            choices[i] = getChoices(i);
        }
    }

    public void done() {

    }

    public void onChoose() {

    }

    public final void setPage(int page) {
        this.page = page;
        desc = getDescription(this.page);
        if (Labyrintale.eventScreen.event == this) {
            Labyrintale.eventScreen.setPage(this.page);
        }
        onSetPage(page);
    }

    public void onSetPage(int page) {

    }

    public void endBattle() {

    }

    public String getDescription(int page) {
        return data.DESC[page];
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

    protected Sprite getImage(int index) {
        return FileHandler.getEventImg().get(data.IMAGE[index]);
    }

    public static abstract class EventChoice {
        public String text;
        public boolean isUsed = false;
        public EventCondition condition;

        public EventChoice(String text) {
            this(text, new EventCondition.True());
        }

        public EventChoice(String text, EventCondition condition) {
            this.condition = condition;
            this.text = text;
        }

        public final void select() {
            onSelect();
            condition.onSelect();
            isUsed = true;
        }

        protected abstract void onSelect();

        public final boolean available() {
            return !isUsed && condition.condition();
        }
    }

    public static abstract class EventCondition {
        public abstract boolean condition();

        public abstract String cdText();

        public void onSelect() {
        }

        public static class True extends EventCondition {
            @Override
            public boolean condition() {
                return true;
            }

            @Override
            public String cdText() {
                return "";
            }
        }

        public static class False extends EventCondition {
            @Override
            public boolean condition() {
                return false;
            }

            @Override
            public String cdText() {
                return "잠김";
            }
        }
    }
}
