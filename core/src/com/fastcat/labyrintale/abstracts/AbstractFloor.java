package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.events.first.CivilizationEvent;
import com.fastcat.labyrintale.events.first.DoorEvent;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.elite.TestElite;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak1;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak2;
import com.fastcat.labyrintale.rooms.other.*;

import static com.fastcat.labyrintale.abstracts.AbstractWay.WayType.*;

public class AbstractFloor {
    public AbstractRoom currentRoom;
    public AbstractWay currentWay;
    public AbstractWay[] ways = new AbstractWay[13];
    public boolean canBoss;
    public boolean isDone;
    public int floorNum;
    public int num;

    public AbstractFloor(SaveHandler.FloorData data) {
        canBoss = data.canBoss;
        isDone = data.isDone;
        floorNum = data.floorNum;
        num = data.num;
        for(int i = 0; i < 13; i++) {
            ways[i] = new AbstractWay(data.ways[i]);
        }
        if(data.currentWay != null) {
            currentWay = ways[num - 1];
        }
        if(data.currentRoom != null) {
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
        this.canBoss = false;
        this.isDone = false;
        this.num = 0;
        GroupHandler.RoomGroup.eventCount = 0;

        ways[0] = new AbstractWay(generateWay(f, ENTRY), ENTRY);
        for(int i = 1; i < 4; i++) {
            ways[i] = new AbstractWay(generateWay(f, WEAK), NORMAL);
        }
        ways[4] = new AbstractWay(generateWay(f, ELITE), ELITE);
        ways[5] = new AbstractWay(generateWay(f, NORMAL), NORMAL);
        ways[6] = new AbstractWay(generateWay(f, SHOP), SHOP);
        ways[7] = new AbstractWay(generateWay(f, NORMAL), NORMAL);
        ways[8] = new AbstractWay(generateWay(f, ELITE), ELITE);
        for(int i = 9; i < 11; i++) {
            ways[i] = new AbstractWay(generateWay(f, NORMAL), NORMAL);
        }
        ways[11] = new AbstractWay(generateWay(f, REST), REST);
        ways[12] = new AbstractWay(generateWay(f, BOSS), BOSS);

        currentRoom = new PlaceholderRoom();
    }

    public void done() {
        this.isDone = true;
    }

    private Array<AbstractChoice> generateWay(int floor, AbstractWay.WayType type) {

        Array<AbstractChoice> t = new Array<>();
        if(type == ENTRY) {
            if(floor == 1) t.add(new AbstractChoice(new EntryRoom(), AbstractChoice.ChoiceType.GOOD, true));
            else if(floor == 2) t.add(new AbstractChoice(new SecondFloorRoom(), AbstractChoice.ChoiceType.GOOD, true));
            else t.add(new AbstractChoice(new EntryRoom(), AbstractChoice.ChoiceType.GOOD, true));
        } else if(type == WEAK) {
            int x = AbstractLabyrinth.mapRandom.random(100);
            boolean rest = false;
            boolean mystery = false;

            if(x < 50) {
                rest = true;
                mystery = true;
            } else if(x < 90) {
                mystery = true;
            } else{
                rest = true;
            }
            t.add(new AbstractChoice(new Weak2(), AbstractChoice.ChoiceType.BATTLE, true));
            if(mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK, true));
            if(rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            shuffleChoice(t);
        } else if (type == NORMAL) {
            int x = AbstractLabyrinth.mapRandom.random(100);
            boolean rest = false;
            boolean mystery = false;

            if(x < 50) {
                rest = true;
                mystery = true;
            } else if(x < 90) {
                mystery = true;
            } else{
                rest = true;
            }
            t.add(new AbstractChoice(new Weak2(), AbstractChoice.ChoiceType.BATTLE, true));
            if(mystery) t.add(new AbstractChoice(new MysteryRoom(), AbstractChoice.ChoiceType.LOOK, true));
            if(rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            shuffleChoice(t);
        } else if (type == ELITE) {
            t.add(new AbstractChoice(new TestElite(), AbstractChoice.ChoiceType.ELITE, true));
            t.add(new AbstractChoice(new Weak1(), AbstractChoice.ChoiceType.DETOUR, 50));
            shuffleChoice(t);
        } else if (type == BOSS) {
            t.add(new AbstractChoice(new TestBoss(), AbstractChoice.ChoiceType.BOSS, true));
        } else if (type == REST) {
            t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
        } else if(type == SHOP) {
            t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            t.add(new AbstractChoice(new ShopRoom(), AbstractChoice.ChoiceType.SHOP, true));
            shuffleChoice(t);
        }
        return t;
    }

    public static Array<AbstractChoice> shuffleChoice(Array<AbstractChoice> array) {
        AbstractChoice[] items = array.toArray(AbstractChoice.class);
        for(int i = array.size - 1; i >= 0; --i) {
            int ii = AbstractLabyrinth.mapRandom.random(i);
            AbstractChoice temp = items[i];
            items[i] = items[ii];
            items[ii] = temp;
        }
        array.clear();
        array.addAll(items);
        return array;
    }
}
