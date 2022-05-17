package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.events.TestEvent;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.normal.Test;
import com.fastcat.labyrintale.rooms.enemy.elite.TestElite;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak1;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak2;
import com.fastcat.labyrintale.rooms.other.EntryRoom;
import com.fastcat.labyrintale.rooms.other.EventRoom;
import com.fastcat.labyrintale.rooms.other.RestRoom;

import static com.fastcat.labyrintale.abstracts.AbstractWay.WayType.*;

public class AbstractFloor {
    public AbstractRoom currentRoom;
    public AbstractWay currentWay;
    public AbstractWay[] ways = new AbstractWay[13];
    public boolean canBoss;
    public int floorNum;
    public int num;

    public AbstractFloor(SaveHandler.FloorData data) {
        currentWay = new AbstractWay(data.currentWay);
        for(int i = 0; i < 13; i++) {
            ways[i] = new AbstractWay(data.ways[i]);
        }
        canBoss = data.canBoss;
        floorNum = data.floorNum;
        num = data.num;
    }

    public AbstractFloor() {
        this(1);
    }

    public AbstractFloor(int f) {
        this.floorNum = f;
        this.canBoss = false;
        this.num = 0;

        ways[0] = new AbstractWay(generateWay(ENTRY), ENTRY);
        for(int i = 1; i < 4; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[4] = new AbstractWay(generateWay(ELITE), ELITE);
        for(int i = 5; i < 8; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[8] = new AbstractWay(generateWay(ELITE), ELITE);
        for(int i = 9; i < 11; i++) {
            ways[i] = new AbstractWay(generateWay(NORMAL), NORMAL);
        }
        ways[11] = new AbstractWay(generateWay(REST), REST);
        ways[12] = new AbstractWay(generateWay(BOSS), BOSS);
    }

    private Array<AbstractChoice> generateWay(AbstractWay.WayType type) {
        Array<AbstractChoice> t = new Array<>();
        //TODO 랜덤으로 생성하게
        if(type == ENTRY) {
            t.add(new AbstractChoice(new Weak1(), AbstractChoice.ChoiceType.GOOD, true));
            t.add(new AbstractChoice(new Weak1(), AbstractChoice.ChoiceType.GOOD, true));
            t.add(new AbstractChoice(new Weak1(), AbstractChoice.ChoiceType.GOOD, true));
        } else if (type == NORMAL) {
            t.add(new AbstractChoice(new Weak2(), AbstractChoice.ChoiceType.BATTLE, true));
            t.add(new AbstractChoice(new EventRoom(new TestEvent()), AbstractChoice.ChoiceType.LOOK, true));
            t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
        } else if (type == ELITE) {
            t.add(new AbstractChoice(new TestElite(), AbstractChoice.ChoiceType.ELITE, true));
            t.add(new AbstractChoice(new Weak1(), AbstractChoice.ChoiceType.DETOUR, 50));
        } else if (type == BOSS) {
            t.add(new AbstractChoice(new TestBoss(), AbstractChoice.ChoiceType.BOSS, true));
        } else if (type == REST) {
            t.add(new AbstractChoice(new RestRoom(), AbstractChoice.ChoiceType.REST, true));
        }
        return t;
    }
}
