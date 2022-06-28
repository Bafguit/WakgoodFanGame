package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.events.*;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.elite.TestElite;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak1;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak2;
import com.fastcat.labyrintale.rooms.other.EntryRoom;
import com.fastcat.labyrintale.rooms.other.PlaceholderRoom;
import com.fastcat.labyrintale.rooms.other.RestRoom;
import com.fastcat.labyrintale.rooms.other.ShopRoom;

import java.util.Objects;

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

        ways[0] = new AbstractWay(generateWay(ENTRY), ENTRY);
        for(int i = 1; i < 4; i++) {
            ways[i] = new AbstractWay(generateWay(WEAK), NORMAL);
        }
        ways[4] = new AbstractWay(generateWay(ELITE), ELITE);
        ways[5] = new AbstractWay(generateWay(NORMAL), NORMAL);
        ways[6] = new AbstractWay(generateWay(SHOP), SHOP);
        ways[7] = new AbstractWay(generateWay(NORMAL), NORMAL);
        ways[8] = new AbstractWay(generateWay(ELITE), ELITE);
        for(int i = 9; i < 11; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[11] = new AbstractWay(generateWay(REST), REST);
        ways[12] = new AbstractWay(generateWay(BOSS), BOSS);

        currentRoom = new PlaceholderRoom();
    }

    public void done() {
        this.isDone = true;
    }

    private Array<AbstractChoice> generateWay(AbstractWay.WayType type) {
        int x = AbstractLabyrinth.mapRandom.random(100);
        boolean battle = false;
        boolean rest = false;
        boolean mystery = false;

        if(x < 60) {
            battle = true;
            rest = true;
            mystery = true;
        } else {
            int y = AbstractLabyrinth.mapRandom.random(100);
            if(y < 60) {
                battle = true;
                mystery = true;
            } else if(y < 90) {
                battle = true;
                rest = true;
            } else {
                rest = true;
                mystery = true;
            }
        }

        Array<AbstractChoice> t = new Array<>();
        //TODO 랜덤으로 생성하게
        if(type == ENTRY) {
            t.add(new AbstractChoice(new EntryRoom(), AbstractChoice.ChoiceType.GOOD, true));
        } else if(type == WEAK) {
            if(battle) t.add(new AbstractChoice(new Weak2(), AbstractChoice.ChoiceType.BATTLE, true));
            if(mystery) t.add(new AbstractChoice(new AbstractRoom(new CivilizationEvent()), AbstractChoice.ChoiceType.LOOK, true));
            if(rest) t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
            shuffleChoice(t);
        } else if (type == NORMAL) {
            if(battle) t.add(new AbstractChoice(new Weak2(), AbstractChoice.ChoiceType.BATTLE, true));
            if(mystery) t.add(new AbstractChoice(new AbstractRoom(new DoorEvent()), AbstractChoice.ChoiceType.LOOK, true));
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
            t.add(new AbstractChoice(new ShopRoom(), AbstractChoice.ChoiceType.SHOP, true));
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
