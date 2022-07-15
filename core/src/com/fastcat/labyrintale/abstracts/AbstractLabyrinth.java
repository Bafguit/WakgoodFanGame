package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.RestrictionHandler;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.players.*;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class AbstractLabyrinth {

    public static ActionHandler actionHandler;

    public static String seed;
    public static long seedLong;

    public static RandomXC publicRandom;
    public static RandomXC skillRandom;
    public static RandomXC itemRandom;
    public static RandomXC mapRandom;
    public static RandomXC monsterRandom;
    public static RandomXC eventRandom;
    public static RandomXC shopRandom;
    public static RandomXC groupRandom;
    public static RestrictionHandler restriction;
    public static AbstractFloor[] floors;
    public static AbstractFloor currentFloor;
    public static AbstractPlayer[] players;
    public static AbstractAdvisor advisor;
    public static ControlPanel cPanel;
    public static int floorNum;
    public static int itemAble;
    public static int maxSlotUp;
    public static int maxEnergy;
    public static int energy;
    public static int gold;
    public static int bleak;
    public static int bleakMin;
    public static int bleakAdd;

    public AbstractLabyrinth() {
        this(RunType.NEW);
    }

    public AbstractLabyrinth(RunType type) {
        actionHandler = new ActionHandler();
        players = new AbstractPlayer[4];
        restriction = new RestrictionHandler();
        if(type == RunType.SAVE) {
            SaveHandler.load();
        } else {
            if(seed.equals("")) seed = generateRandomSeed();
            seedLong = seedToLong(seed);
            publicRandom = new RandomXC(seedLong);
            skillRandom = new RandomXC(seedLong);
            itemRandom = new RandomXC(seedLong);
            mapRandom = new RandomXC(seedLong);
            monsterRandom = new RandomXC(seedLong);
            eventRandom = new RandomXC(seedLong);
            shopRandom = new RandomXC(seedLong);
            groupRandom = new RandomXC(seedLong);
            GroupHandler.RoomGroup.shuffleAll();
            floors = new AbstractFloor[4];
            currentFloor = new AbstractFloor();
            floors[0] = currentFloor;
            floorNum = currentFloor.floorNum;
            floors[1] = new AbstractFloor(2);
            floors[2] = new AbstractFloor(3);
            floors[3] = new AbstractFloor(4);
            itemAble = 0;
            maxSlotUp = 3;
            maxEnergy = 3;
            energy = 0;
            gold = 1000;
            bleak = 0;
            bleakMin = 0;
            bleakAdd = 9;
            for (int i = 0; i < 4; i++) {
                AbstractPlayer p = getPlayerInstance(Labyrintale.charSelectScreen.chars[i].selected);
                p.defineIndex(i);
                restriction.onCreatePlayer(p);
                Array<AbstractItem> t = p.getStartingItem();
                for(int j = 0; j < 2; j++) {
                    AbstractItem item = t.get(j);
                    item.onGain();
                    p.item[j] = item;
                }
                players[i] = p;
            }
            restriction.onCreateLabyrinth();
        }
        cPanel = new ControlPanel();
    }

    public void update() {

    }

    public static void prepare() {
        energy = maxEnergy;
        advisor.skill.usedOnce = false;
        advisor.skill.cooldown = 0;
    }

    public static boolean hasSlot() {
        boolean hasSlot = false;
        for(int i = 0; i < 4; i++) {
            AbstractPlayer p = AbstractLabyrinth.players[i];
            if(p.isAlive() && p.hasSlot()) hasSlot = true;
        }
        return hasSlot;
    }

    public static void addBleak() {
        bleak = Math.min(bleak + (9 + floorNum), 100);
    }

    public static void addBleak(int add) {
        bleak = Math.max(Math.min(bleak + add, 100), 0);
    }

    public static void reduceBleak() {
        bleak = Math.max(bleak - (11 - floorNum), 0);
    }

    public static boolean canGetItem() {
        return itemAble == 0;
    }

    public static void modifyItemAble(int a) {
        itemAble = Math.max(itemAble + a, 0);
    }

    public static void modifyMaxEnergy(int a) {
        maxEnergy = Math.max(maxEnergy + a, 0);
    }

    public static void modifySelection(int a) {
        maxSlotUp = Math.max(maxSlotUp + a, 0);
    }

    private static String generateRandomSeed() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            boolean t = MathUtils.randomBoolean();
            char c;
            if(t) {
                c = (char) MathUtils.random(48, 57);
            } else {
                c = (char) MathUtils.random(65, 90);
            }
            s.append(c);
        }
        return s.toString();
    }

    public static void endRoom() {
        if(currentFloor.currentRoom.type != AbstractRoom.RoomType.BATTLE && currentFloor.currentRoom.type != AbstractRoom.RoomType.ELITE && currentFloor.currentRoom.type != AbstractRoom.RoomType.BOSS) {
            AbstractLabyrinth.addBleak();
        }
        currentFloor.currentWay.done();
        currentFloor.currentRoom.done();
        if(currentFloor.num == 11) {
            currentFloor.canBoss = true;
        } else if (currentFloor.canBoss) {
            currentFloor.done();
            currentFloor = floors[++floorNum];
        }
        Labyrintale.mapScreen.isView = false;
        SaveHandler.save();
    }

    public static long seedToLong(String s) {
        char[] ca = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : ca) {
            sb.append((int)c);
        }
        return Long.parseLong(sb.toString());
    }

    public static AbstractPlayer getPlayerInstance(AbstractPlayer.PlayerClass cls) {
        switch (cls) {
            case MANAGER:
                return new Manager();
            case INE:
                return new Ine();
            case VIICHAN:
                return new Viichan();
            case LILPA:
                return new Lilpa();
            case BURGER:
                return new Burger();
            case GOSEGU:
                return new Gosegu();
            case JURURU:
                return new Jururu();
            default:
                return new Wakgood();
        }
    }

    public static void victoryRoom() {
        advisor.skill.usedOnce = false;
        advisor.skill.cooldown = 0;
        currentFloor.currentWay.done();
        currentFloor.currentRoom.done();
        AbstractPlayer[] temp = players;
        players = new AbstractPlayer[4];
        for(AbstractPlayer p : temp) {
            p.mRightTemp = p.mRight;
            p.mLeftTemp = p.mLeft;
            players[p.index] = p;
        }
        //AbstractLabyrinth.prepare();
        AbstractLabyrinth.reduceBleak();
        SaveHandler.save();
    }

    public enum RunType {
        NEW, SAVE
    }
}
