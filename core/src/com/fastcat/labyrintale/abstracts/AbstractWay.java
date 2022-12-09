package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;

public class AbstractWay {
    public Sprite img = FileHandler.getCharImg().get(AbstractPlayer.PlayerClass.BURGER);
    public WayType type;
    public AbstractRoom enemies;
    public int selected = -1;
    public final AbstractChoice[] choices;
    public boolean isDone = false;

    public AbstractWay(SaveHandler.WayData data) {
        type = WayType.valueOf(data.type);
        if (data.enemies != null) {
            enemies = GroupHandler.RoomGroup.getRoom(data.enemies.id);
            enemies.isDone = data.enemies.isDone;
            enemies.battleDone = data.enemies.battleDone;
        }
        int l = data.choices.length;
        choices = new AbstractChoice[3];
        for (int i = 0; i < l; i++) {
            if (data.choices[i] != null) choices[i] = new AbstractChoice(data.choices[i]);
        }
        isDone = data.isDone;
        selected = data.selected;
    }

    public AbstractWay(AbstractChoice c, WayType t) {
        choices = new AbstractChoice[3];
        choices[1] = c;
        type = t;
    }

    public AbstractWay(AbstractChoice c, AbstractRoom e, WayType t) {
        choices = new AbstractChoice[3];
        choices[1] = c;
        type = t;
        enemies = e;
    }

    public AbstractWay(AbstractChoice[] s, WayType t) {
        choices = s;
        type = t;
    }

    public AbstractWay(AbstractChoice[] s, AbstractRoom e, WayType t) {
        choices = s;
        type = t;
        enemies = e;
    }

    public void done() {
        isDone = true;
        for (int i = 0; i < 3; i++) {
            AbstractChoice c = choices[i];
            if (c != null) c.room.done();
        }
    }

    public enum WayType {
        ENTRY,
        REST,
        SHOP,
        WEAK,
        NORMAL,
        ELITE,
        BOSS
    }
}
