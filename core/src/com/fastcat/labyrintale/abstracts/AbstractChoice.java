package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.ChoiceString;

import java.util.ArrayList;

public class AbstractChoice {

    public ChoiceString.ChoiceData data;
    public ChoiceType type;
    public AbstractRoom room;
    public String name;
    public String desc;
    public String[] rawDesc;
    public int index;
    public Sprite img = FileHandler.getUi().get("DRAW");
    public ArrayList<Integer> linked = new ArrayList<>();
    public ArrayList<Integer> linked2 = new ArrayList<>();
    public boolean isFirst = false;
    public boolean isLast = false;

    public AbstractChoice(SaveHandler.ChoiceData data) {
        this(GroupHandler.RoomGroup.getRoom(data.room.id), ChoiceType.valueOf(data.type));
        room.isDone = data.room.isDone;
        room.battleDone = data.room.battleDone;
        linked = data.linked;
        linked2 = data.linked2;
    }

    public AbstractChoice(AbstractRoom r, ChoiceType t) {
        data = StringHandler.choiceString.get(t.toString().toLowerCase());
        name = data.NAME;
        rawDesc = data.DESC;
        room = r;
        type = t;
        desc = rawDesc[0];
        if(t == ChoiceType.ENTRY) {
            isFirst = true;
            index = 1;
        }
        else if(t == ChoiceType.BOSS) {
            isLast = true;
            index = 1;
        } else if(t == ChoiceType.ELITE) index = 1;
    }

    public void link(AbstractFloor floor, int way) {
        if(isFirst || floor.ways[way].choices.length == 1) {
            AbstractWay w = floor.ways[way + 1];
            for (int i = 0; i < 3; i++) {
                AbstractChoice c = w.choices[i];
                if (c != null) {
                    addLink(i);
                    c.addLink2(index);
                }
            }
        } else {
            Array<Integer> temp = new Array<>();
            AbstractWay w = floor.ways[way + 1];
            for (int i = 0; i < 3; i++) {
                AbstractChoice c = w.choices[i];
                if (c != null) temp.add(i);
            }
            Integer[] items = temp.toArray(Integer.class);
            for (int i = temp.size - 1; i >= 0; --i) {
                int ii = AbstractLabyrinth.mapRandom.random(i);
                int temp1 = items[i];
                items[i] = items[ii];
                items[ii] = temp1;
            }
            if (items.length > 0) {
                addLink(items[0]);
                w.choices[items[0]].addLink2(index);
                /*if (items.length > 1) {
                    int rand = AbstractLabyrinth.mapRandom.random(100);
                    if (rand >= 70) {
                        addLink(items[1]);
                        w.choices[items[1]].addLink2(index);
                    }
                }*/
            }
        }
        if(!isFirst && linked2.size() == 0) {
            Array<Integer> temp = new Array<>();
            AbstractWay w = floor.ways[way - 1];
            for (int i = 0; i < 3; i++) {
                AbstractChoice c = w.choices[i];
                if (c != null) temp.add(i);
            }
            Integer[] items = temp.toArray(Integer.class);
            for (int i = temp.size - 1; i >= 0; --i) {
                int ii = AbstractLabyrinth.mapRandom.random(i);
                int temp1 = items[i];
                items[i] = items[ii];
                items[ii] = temp1;
            }
            if (items.length > 0) {
                addLink2(items[0]);
                w.choices[items[0]].addLink(index);
            }
        }
    }

    public void addLink(int index) {
        linked.add(index);
    }

    public void addLink2(int index) {
        isFirst = false;
        linked2.add(index);
    }

    public enum ChoiceType {
        BATTLE, ELITE, BOSS, REST, LOOK, SHOP, ENTRY
    }
}
