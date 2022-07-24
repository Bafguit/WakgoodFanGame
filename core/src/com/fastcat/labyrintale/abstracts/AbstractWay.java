package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;

public class AbstractWay {
    public Sprite img = FileHandler.getCharImg().get(AbstractPlayer.PlayerClass.BURGER);
    public WayType type;
    public AbstractRoom enemies;
    public AbstractChoice[] choices;
    public boolean isDone = false;

    public AbstractWay(SaveHandler.WayData data) {
        type = WayType.valueOf(data.type);
        if (data.enemies != null) {
            enemies = GroupHandler.RoomGroup.getRoom(data.enemies.id);
            enemies.isDone = data.enemies.isDone;
            enemies.battleDone = data.enemies.battleDone;
        }
        int l = data.choices.length;
        choices = new AbstractChoice[l];
        for (int i = 0; i < l; i++) {
            choices[i] = new AbstractChoice(data.choices[i]);
        }
        isDone = data.isDone;
    }

    public AbstractWay(AbstractChoice c, WayType t) {
        choices = new AbstractChoice[1];
        choices[0] = c;
        type = t;
    }

    public AbstractWay(AbstractChoice c, AbstractRoom e, WayType t) {
        choices = new AbstractChoice[1];
        choices[0] = c;
        type = t;
        enemies = e;
    }

    public AbstractWay(Array<AbstractChoice> s, WayType t) {
        choices = s.toArray(AbstractChoice.class);
        type = t;
    }

    public AbstractWay(Array<AbstractChoice> s, AbstractRoom e, WayType t) {
        choices = s.toArray(AbstractChoice.class);
        type = t;
        enemies = e;
    }

    public void done() {
        isDone = true;
    }

    public enum WayType {
        ENTRY, REST, SHOP, WEAK, NORMAL, ELITE, BOSS
    }
}
