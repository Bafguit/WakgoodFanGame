package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.rooms.other.*;
import com.fastcat.labyrintale.screens.map.MapNodeButton;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.abstracts.AbstractWay.WayType.*;

public class AbstractFloor {
    public AbstractRoom currentRoom;
    public AbstractWay currentWay;
    public AbstractWay[] ways = new AbstractWay[13];
    public boolean isDone;
    public int floorNum;
    public int num;

    public AbstractFloor(SaveHandler.FloorData data) {
        isDone = data.isDone;
        floorNum = data.floorNum;
        num = data.num;
        for (int i = 0; i < 13; i++) {
            AbstractWay w = new AbstractWay(data.ways[i]);
            for(int j = 0; j < 3; j++) {
                AbstractChoice c = w.choices[j];
                if(c != null) c.index = j;
            }
            ways[i] = w;
        }
        currentWay = ways[num];
        if (data.currentRoom != null) {
            currentRoom = GroupHandler.RoomGroup.getRoom(data.currentRoom.id);
            currentRoom.isDone = data.currentRoom.isDone;
            currentRoom.battleDone = data.currentRoom.battleDone;
        }
    }

    public AbstractFloor() {
        this(1);
    }

    public AbstractFloor(int f) {
        this.floorNum = f;
        this.isDone = false;
        this.num = 0;
        GroupHandler.RoomGroup.eventCount = 0;
        GroupHandler.RoomGroup.weakCount = 0;
        GroupHandler.RoomGroup.normalCount = 0;
        GroupHandler.RoomGroup.eliteCount = 0;
        GroupHandler.RoomGroup.bossCount = 0;

        ways[0] = generateWay(f, 0, ENTRY);
        for (int i = 1; i < 4; i++) {
            ways[i] = generateWay(f, i, WEAK);
        }
        ways[4] = generateWay(f, 4, ELITE);
        ways[5] = generateWay(f, 5, NORMAL);
        ways[6] = generateWay(f, 6, SHOP);
        ways[7] = generateWay(f, 7, NORMAL);
        ways[8] = generateWay(f, 8, ELITE);
        for (int i = 9; i < 11; i++) {
            ways[i] = generateWay(f, i, NORMAL);
        }
        ways[11] = generateWay(f, 11, REST);
        ways[12] = generateWay(f, 12, BOSS);

        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 3; j++) {
                AbstractChoice c = ways[i].choices[j];
                if(c != null) {
                    c.index = j;
                    c.link(this, i);
                }
            }
        }

        currentWay = ways[0];
        currentRoom = new PlaceholderRoom();
    }

    public static Array<AbstractChoice> shuffleChoice(Array<AbstractChoice> array) {
        AbstractChoice[] items = array.toArray(AbstractChoice.class);
        for (int i = array.size - 1; i >= 0; --i) {
            int ii = AbstractLabyrinth.mapRandom.random(i);
            AbstractChoice temp = items[i];
            items[i] = items[ii];
            items[ii] = temp;
        }
        array.clear();
        array.addAll(items);
        return array;
    }

    public static AbstractChoice[] shuffleChoice(AbstractChoice[] items) {
        int l = items.length;
        for (int i = l - 1; i >= 0; --i) {
            int ii = AbstractLabyrinth.mapRandom.random(i);
            AbstractChoice temp = items[i];
            items[i] = items[ii];
            items[ii] = temp;
        }
        return items;
    }

    public void update() {
        for (int i = 1; i < 13; i++) {
            int s = currentFloor.ways[i - 1].selected;
            int s2 = currentFloor.ways[i].selected;
            for(int j = 0; j < 3; j++) {
                AbstractChoice c = ways[i].choices[j];
                if(c != null) {
                    boolean can = false;
                    for (int k : c.linked2) {
                        AbstractChoice c3 = ways[i - 1].choices[k];
                        if(c3.canGo && (s == -1 || s == k) && (s2 == -1 || s2 == j)) {
                            can = true;
                            break;
                        }
                    }
                    c.canGo = can;
                }
            }
        }
    }

    public void done() {
        this.isDone = true;
    }

    private AbstractWay generateWay(int floor, int way, AbstractWay.WayType type) {
        AbstractWay w;
        if (type == ENTRY) {
            if (floor == 1) w = new AbstractWay(new AbstractChoice(new EntryRoom(), AbstractChoice.ChoiceType.ENTRY), type);
            else if (floor == 2) w = new AbstractWay(new AbstractChoice(new SecondFloorRoom(), AbstractChoice.ChoiceType.ENTRY), type);
            else w = new AbstractWay(new AbstractChoice(new SecondFloorRoom(), AbstractChoice.ChoiceType.ENTRY), type);
        } else if (type == WEAK) {
            int x = AbstractLabyrinth.mapRandom.random(0, 100);
            boolean battle = false;
            boolean battle2 = false;
            boolean mystery = false;
            boolean mystery2 = false;

            if (x < 30) {
                mystery = true;
            } else if (x < 50) {
                battle = true;
            }  else if (x < 80) {
                battle = true;
                mystery = true;
            } else if (x < 90) {
                battle = true;
                battle2 = true;
            } else {
                mystery = true;
                mystery2 = true;
            }
            AbstractRoom r = GroupHandler.RoomGroup.getNextWeak(floor);
            AbstractChoice[] aa = new AbstractChoice[3];
            Array<AbstractChoice> t = new Array<>();
            t.add(new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE));
            if (battle) t.add(new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE));
            if (battle2) t.add(new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE));
            if (mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK));
            if (mystery2) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK));
            for(int i = 0; i < t.size; i++) {
                aa[i] = t.get(i);
            }
            shuffleChoice(aa);
            w = new AbstractWay(aa, r, type);
        } else if (type == NORMAL) {
            int x = AbstractLabyrinth.mapRandom.random(100);
            boolean battle = false;
            boolean battle2 = false;
            boolean mystery = false;
            boolean mystery2 = false;

            if (x < 20) {
                mystery = true;
            } else if (x < 50) {
                battle = true;
            }  else if (x < 65) {
                battle = true;
                mystery = true;
            } else if (x < 90) {
                battle = true;
                battle2 = true;
            } else {
                mystery = true;
                mystery2 = true;
            }
            AbstractRoom r = GroupHandler.RoomGroup.getNextNormal(floor);
            AbstractChoice[] aa = new AbstractChoice[3];
            Array<AbstractChoice> t = new Array<>();
            t.add(new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE));
            if (battle) t.add(new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE));
            if (battle2) t.add(new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE));
            if (mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK));
            if (mystery2) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK));
            for(int i = 0; i < t.size; i++) {
                aa[i] = t.get(i);
            }
            shuffleChoice(aa);
            w = new AbstractWay(aa, r, type);
        } else if (type == ELITE) {
            AbstractChoice[] aa = new AbstractChoice[3];
            AbstractRoom r = GroupHandler.RoomGroup.getNextElite(floor);
            aa[0] = new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST);
            aa[1] = new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.ELITE);
            shuffleChoice(aa);
            w = new AbstractWay(aa, r, type);
        } else if (type == BOSS) {
            AbstractRoom r = GroupHandler.RoomGroup.getNextBoss(floor);
            w = new AbstractWay(new AbstractChoice(r, AbstractChoice.ChoiceType.BOSS), r, type);
        } else if (type == REST) {
            w = new AbstractWay(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST), type);
        } else {
            AbstractRoom r = GroupHandler.RoomGroup.getNextNormal(floor);
            AbstractChoice[] aa = new AbstractChoice[3];
            aa[1] = new AbstractChoice(new ShopRoom(), AbstractChoice.ChoiceType.SHOP);
            aa[2] = new AbstractChoice(r.clone(), AbstractChoice.ChoiceType.BATTLE);
            shuffleChoice(aa);
            w = new AbstractWay(aa, type);
        }
        return w;
    }
}
