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
    public int page = 0;
    public EventChoice[] choices;

    public AbstractEvent(String id, int size) {
        this.id = id;
        data = StringHandler.eventString.data.get(this.id);
        name = data.NAME;
        choices = new EventChoice[size];
    }

    public void done() {

    }

    protected void onChoose() {

    }

    public static abstract class EventChoice {
        public String text;
        public AbstractEvent event;
        public boolean isUsed = false;

        public EventChoice(AbstractEvent event, String text) {
            this.text = text;
            this.event = event;
        }

        public final void select() {
            onSelect();
            event.onChoose();
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
