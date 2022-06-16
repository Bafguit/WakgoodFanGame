package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.advisors.TestAdvisor;
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
    public static RestrictionHandler restriction;
    public static AbstractFloor[] floors;
    public static AbstractFloor currentFloor;
    public static AbstractPlayer[] players;
    public static AbstractAdvisor advisor;
    public static ControlPanel cPanel;
    public static int floorNum;
    public static int itemAble;
    public static int selection;
    public static int maxEnergy;
    public static int energy;
    public static int gold;
    public static int bleak;
    public static int bleakMin;

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
            floors = new AbstractFloor[4];
            currentFloor = new AbstractFloor();
            floors[0] = currentFloor;
            floorNum = currentFloor.floorNum;
            floors[1] = new AbstractFloor(2);
            floors[2] = new AbstractFloor(3);
            floors[3] = new AbstractFloor(4);
            itemAble = 0;
            selection = 2;
            maxEnergy = 3;
            energy = 0;
            gold = 1000;
            bleak = 0;
            bleakMin = 0;
            for (int i = 0; i < 4; i++) {
                AbstractPlayer p = getPlayerInstance(Labyrintale.charSelectScreen.chars[i].selected);
                p.defineIndex(i);
                Array<AbstractItem> t = p.getStartingItem();
                for(int j = 0; j < 2; j++) {
                    AbstractItem item = t.get(j);
                    item.onGain();
                    p.item[j] = item;
                }
                players[i] = p;
            }
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
        selection = Math.max(selection + a, 0);
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

    public static void finishRoom() {
        advisor.skill.usedOnce = false;
        advisor.skill.cooldown = 0;
        currentFloor.currentWay.done();
        currentFloor.currentRoom.done();
        AbstractPlayer p1 = players[0];
        AbstractPlayer p2 = players[1];
        AbstractPlayer p3 = players[2];
        AbstractPlayer p4 = players[3];
        for(AbstractPlayer p : players) {
            p.tempIndex = p.index;
            p.mRightTemp = p.mRight;
            p.mLeftTemp = p.mLeft;
        }
        players[p1.index] = p1;
        players[p2.index] = p2;
        players[p3.index] = p3;
        players[p4.index] = p4;
    }

    public enum RunType {
        NEW, SAVE
    }
}
