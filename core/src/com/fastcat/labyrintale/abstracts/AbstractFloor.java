package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.rooms.other.*;

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
            ways[i] = new AbstractWay(data.ways[i]);
        }
        if (data.currentWay != null) {
            currentWay = ways[num - 1];
        }
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
            int x = AbstractLabyrinth.mapRandom.random(100);
            boolean rest = false;
            boolean mystery = false;

            if (x < 30) {
                rest = true;
                mystery = true;
            } else if (x < 90) {
                mystery = true;
            } else {
                rest = true;
            }
            AbstractRoom r = GroupHandler.RoomGroup.getNextWeak(floor);
            AbstractChoice[] aa = new AbstractChoice[3];
            Array<AbstractChoice> t = new Array<>();
            t.add(new AbstractChoice(r, AbstractChoice.ChoiceType.BATTLE));
            if (mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK));
            if (rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST));
            for(int i = 0; i < t.size; i++) {
                aa[i] = t.get(i);
            }
            shuffleChoice(aa);
            w = new AbstractWay(aa, r, type);
        } else if (type == NORMAL) {
            int x = AbstractLabyrinth.mapRandom.random(100);
            boolean rest = false;
            boolean mystery = false;

            if (x < 50) {
                rest = true;
                mystery = true;
            } else if (x < 90) {
                mystery = true;
            } else {
                rest = true;
            }
            AbstractRoom r = GroupHandler.RoomGroup.getNextNormal(floor);
            AbstractChoice[] aa = new AbstractChoice[3];
            Array<AbstractChoice> t = new Array<>();
            t.add(new AbstractChoice(r, AbstractChoice.ChoiceType.BATTLE));
            if (mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK));
            if (rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST));
            for(int i = 0; i < t.size; i++) {
                aa[i] = t.get(i);
            }
            shuffleChoice(aa);
            w = new AbstractWay(aa, r, type);
        } else if (type == ELITE) {
            AbstractRoom r = GroupHandler.RoomGroup.getNextElite(floor);
            w = new AbstractWay(new AbstractChoice(r, AbstractChoice.ChoiceType.ELITE), r, type);
        } else if (type == BOSS) {
            AbstractRoom r = GroupHandler.RoomGroup.getNextBoss(floor);
            w = new AbstractWay(new AbstractChoice(r, AbstractChoice.ChoiceType.BOSS), r, type);
        } else if (type == REST) {
            w = new AbstractWay(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST), type);
        } else {
            AbstractChoice[] aa = new AbstractChoice[3];
            Array<AbstractChoice> t = new Array<>();
            t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST));
            t.add(new AbstractChoice(new ShopRoom(), AbstractChoice.ChoiceType.SHOP));
            for(int i = 0; i < t.size; i++) {
                aa[i] = t.get(i);
            }
            shuffleChoice(aa);
            w = new AbstractWay(aa, type);
        }
        return w;
    }
}
