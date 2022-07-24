package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.elite.act1.Elite2;
import com.fastcat.labyrintale.rooms.enemy.normal.act1.Normal1;
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

        ways[0] = generateWay(f, ENTRY);
        for (int i = 1; i < 4; i++) {
            ways[i] = generateWay(f, WEAK);
        }
        ways[4] = generateWay(f, ELITE);
        ways[5] = generateWay(f, NORMAL);
        ways[6] = generateWay(f, SHOP);
        ways[7] = generateWay(f, NORMAL);
        ways[8] = generateWay(f, ELITE);
        for (int i = 9; i < 11; i++) {
            ways[i] = generateWay(f, NORMAL);
        }
        ways[11] = generateWay(f, REST);
        ways[12] = generateWay(f, BOSS);

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

    public void done() {
        this.isDone = true;
    }

    private AbstractWay generateWay(int floor, AbstractWay.WayType type) {
        AbstractWay w;
        Array<AbstractChoice> t = new Array<>();
        if (type == ENTRY) {
            if (floor == 1) t.add(new AbstractChoice(new EntryRoom(), AbstractChoice.ChoiceType.GOOD, true));
            else if (floor == 2) t.add(new AbstractChoice(new SecondFloorRoom(), AbstractChoice.ChoiceType.GOOD, true));
            else t.add(new AbstractChoice(new EntryRoom(), AbstractChoice.ChoiceType.GOOD, true));
            w = new AbstractWay(t, type);
        } else if (type == WEAK) {
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
            AbstractRoom r = GroupHandler.RoomGroup.getNextWeak(floorNum);
            t.add(new AbstractChoice(r, AbstractChoice.ChoiceType.BATTLE, true));
            if (mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK, true));
            if (rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            shuffleChoice(t);
            w = new AbstractWay(t, r, type);
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
            AbstractRoom r = new Normal1();
            t.add(new AbstractChoice(r, AbstractChoice.ChoiceType.BATTLE, true));
            if (mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK, true));
            if (rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            shuffleChoice(t);
            w = new AbstractWay(t, r, type);
        } else if (type == ELITE) {
            AbstractRoom r = new Elite2();
            w = new AbstractWay(new AbstractChoice(r, AbstractChoice.ChoiceType.ELITE, true), r, type);
        } else if (type == BOSS) {
            AbstractRoom r = new TestBoss();
            w = new AbstractWay(new AbstractChoice(r, AbstractChoice.ChoiceType.BOSS, true), r, type);
        } else if (type == REST) {
            w = new AbstractWay(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true), type);
        } else {
            t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            t.add(new AbstractChoice(new ShopRoom(), AbstractChoice.ChoiceType.SHOP, true));
            shuffleChoice(t);
            w = new AbstractWay(t, type);
        }
        return w;
    }
}
