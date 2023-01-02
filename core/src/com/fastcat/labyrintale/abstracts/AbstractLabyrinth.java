package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractRoom.RoomType;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.interfaces.AtCartoonEnd;
import com.fastcat.labyrintale.players.*;
import com.fastcat.labyrintale.screens.cartoon.CartoonScreen;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.map.MapScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fastcat.labyrintale.utils.RandomXC;
import java.util.HashMap;

public class AbstractLabyrinth {

    public static final int MAX_ENERGY = 8;
    public static float tick;
    public static String date;

    public static String seed;
    public static long seedLong;

    public static AchieveHandler.AchvLabCheck achvCheck;
    public static RandomXC publicRandom;
    public static RandomXC skillRandom;
    public static RandomXC itemRandom;
    public static RandomXC mapRandom;
    public static RandomXC monsterRandom;
    public static RandomXC eventRandom;
    public static RandomXC shopRandom;
    public static RandomXC groupRandom;
    public static DifficultyHandler restriction;
    public static DeadScreen.ScreenType result;
    public static Difficulty diff = Difficulty.NORMAL;
    public static ScoreHandle scoreHandle;
    public static AbstractFloor[] floors;
    public static AbstractFloor currentFloor;
    public static AbstractPlayer[] players;
    public static AbstractItem advisor;
    public static ControlPanel cPanel;
    public static Sprite curBg;
    public static int minute;
    public static int second;
    public static int floorNum;
    public static int itemAble;
    public static int maxSkillUp;
    public static int energy = 0;
    public static int charge;
    public static int gold;
    public static int level;
    public static int maxExp;
    public static int exp;
    public static int sp;

    public AbstractLabyrinth() {
        this(RunType.NEW);
    }

    @SuppressWarnings("NewApi")
    public AbstractLabyrinth(RunType type) {
        players = new AbstractPlayer[4];
        restriction = DifficultyHandler.getInstance();
        if (type == RunType.SAVE) {
            SaveHandler.load();
        } else {
            if (seed.equals("")) seed = generateRandomSeed();
            seedLong = seedToLong(seed);
            publicRandom = new RandomXC(seedLong);
            skillRandom = new RandomXC(seedLong);
            itemRandom = new RandomXC(seedLong);
            mapRandom = new RandomXC(seedLong);
            monsterRandom = new RandomXC(seedLong);
            eventRandom = new RandomXC(seedLong);
            shopRandom = new RandomXC(seedLong);
            groupRandom = new RandomXC(seedLong);
            GroupHandler.RoomGroup.generateRoom();
            GroupHandler.RoomGroup.shuffleAll();
            floorNum = 1;
            floors = new AbstractFloor[4];
            currentFloor = new AbstractFloor();
            floors[0] = currentFloor;
            floors[1] = new AbstractFloor(2);
            floors[2] = new AbstractFloor(3);
            floors[3] = new AbstractFloor(4);
            tick = 0;
            minute = 0;
            second = 0;
            itemAble = 0;
            maxSkillUp = 1;
            gold = 100;
            level = 1;
            exp = 0;
            maxExp = 100;
            charge = 5;
            sp = 0;
            for (int i = 0; i < 4; i++) {
                AbstractPlayer temp = Labyrintale.charSelectScreen.chars[i].player;
                AbstractPlayer p = getPlayerInstance(temp.playerClass);
                p.setCustomSkin(temp.key);
                SettingHandler.setting.skin.replace(temp.playerClass, temp.key);
                p.defineIndex(i);
                restriction.onCreatePlayer(p);
                players[i] = p;
            }
            for (AbstractPlayer p : players) {
                if (p.playerClass == AbstractPlayer.PlayerClass.JURURU) {
                    for (AbstractPlayer pl : players) {
                        if (pl.playerClass != AbstractPlayer.PlayerClass.JURURU) pl.stat.debuRes += 15;
                    }
                    break;
                }
            }
            diff = Labyrintale.charSelectScreen.diff;
            restriction.onCreateLabyrinth();
            scoreHandle = new ScoreHandle();
            achvCheck = new AchieveHandler.AchvLabCheck();
            SettingHandler.save();
        }
        cPanel = new ControlPanel();
        curBg = FileHandler.getBg().get("BG_WAY_" + floorNum);
        Labyrintale.mainMenuScreen.playMusic = true;
    }

    public static void prepare() {
        energy = 0;
        ActionHandler.reset();
    }

    public static void gainExp(int amt) {
        exp += amt;
        if (exp >= maxExp) {
            level++;
            int i = exp - maxExp;
            maxExp *= 1.27f;
            exp = 0;
            // sp += 2;
            for (AbstractPlayer p : players) {
                if (p.isAlive()) {
                    p.modifyMaxHealth(1);
                    p.stat.critical += 1;
                    p.stat.debuRes += 1;
                    p.stat.moveRes += 1;
                    p.stat.neutRes += 1;
                }
            }
            gainExp(i);
        }
    }

    public static boolean hasSlot() {
        boolean hasSlot = false;
        for (int i = 0; i < 4; i++) {
            AbstractPlayer p = AbstractLabyrinth.players[i];
            if (p.isAlive() && p.hasSlot()) hasSlot = true;
        }
        return hasSlot;
    }

    public static void modifyGold(int add) {
        if (add < 0 && achvCheck.NO_USE_GOLD) {
            achvCheck.NO_USE_GOLD = false;
        }
        for (AbstractPlayer p : players) {
            if (add > 0) add = p.passive.onGainGold(add);
            for (AbstractItem i : p.item) {
                if (add > 0) add = i.onGainGold(add);
            }
        }
        gold = Math.max(gold + add, 0);
    }

    public static boolean canGetItem() {
        return itemAble == 0;
    }

    public static void modifyItemAble(int a) {
        itemAble = Math.max(itemAble + a, 0);
    }

    public static void modifySelection(int a) {
        maxSkillUp = Math.max(maxSkillUp + a, 0);
    }

    private static String generateRandomSeed() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            boolean t = MathUtils.randomBoolean();
            char c;
            if (t) {
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
        if (currentFloor.num == 12) {
            if(SettingHandler.setting.cartoon[floorNum - 1]) {
                int f = floorNum;
                SettingHandler.setting.cartoon[f - 1] = false;
                nextFloor();
                SettingHandler.save();
                Labyrintale.fadeOutAndChangeScreen(new CartoonScreen("CLEAR_ACT" + f, 3, Labyrintale::returnToWay));
            } else {
                nextFloor();
                SaveHandler.save();
                Labyrintale.returnToWay();
            }
        } else {
            currentFloor.currentWay = currentFloor.ways[++currentFloor.num];
            SaveHandler.save();
            Labyrintale.returnToWay();
        }
    }

    public static void nextFloor() {
        currentFloor.done();
        currentFloor = floors[floorNum++];
        currentFloor.num = 0;
        curBg = FileHandler.getBg().get("BG_WAY_" + floorNum);
        GroupHandler.RoomGroup.weakCount = 0;
        GroupHandler.RoomGroup.normalCount = 0;
        GroupHandler.RoomGroup.eliteCount = 0;
        GroupHandler.RoomGroup.bossCount = 0;
        GroupHandler.RoomGroup.eventCount = 0;
        Labyrintale.mapScreen = new MapScreen();
    }

    public static long seedToLong(String s) {
        char[] ca = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : ca) {
            sb.append((int) c);
        }
        return Long.parseLong(sb.toString());
    }

    public static void reset() {
        seed = "";
        seedLong = 0;
        publicRandom = null;
        skillRandom = null;
        itemRandom = null;
        mapRandom = null;
        monsterRandom = null;
        eventRandom = null;
        shopRandom = null;
        groupRandom = null;
        floorNum = 1;
        floors = new AbstractFloor[4];
        currentFloor = null;
        advisor = null;
        tick = 0;
        minute = 0;
        second = 0;
        itemAble = 0;
        maxSkillUp = 1;
        gold = 100;
        level = 1;
        exp = 0;
        maxExp = 100;
        charge = 5;
        sp = 0;
        players = new AbstractPlayer[4];
        diff = null;
        cPanel = null;
        achvCheck = new AchieveHandler.AchvLabCheck();
        ActionHandler.reset();
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
        currentFloor.currentWay.done();
        currentFloor.currentRoom.done();

        AbstractPlayer[] temp = players;
        players = new AbstractPlayer[4];
        for (AbstractPlayer p : temp) {
            p.moveTemp = p.move;
            players[p.index] = p;
        }
        // AbstractLabyrinth.prepare();
        if (currentFloor.currentRoom.type == RoomType.BATTLE) {
            scoreHandle.normal++;
        } else if (currentFloor.currentRoom.type == RoomType.ELITE) {
            int s = scoreHandle.elite.get(floorNum) + 1;
            scoreHandle.elite.put(floorNum, s);
        } else if (currentFloor.currentRoom.type == RoomType.BOSS) {
            scoreHandle.boss.put(floorNum, floorNum);
        }
        SaveHandler.save();
    }

    public static boolean allAlive() {
        return players[0].isAlive() && players[1].isAlive() && players[2].isAlive() && players[3].isAlive();
    }

    public static boolean stillAlive() {
        return players[0].isAlive() || players[1].isAlive() || players[2].isAlive() || players[3].isAlive();
    }

    public void update() {
        if (!Labyrintale.setting && !Labyrintale.tutorial) tick();
        if (cPanel != null) cPanel.update();
        if (currentFloor != null) currentFloor.update();
    }

    private void tick() {
        tick += Labyrintale.tick;
        if (tick >= 1) {
            tick = 0;
            second++;
            if (second == 60) {
                minute++;
                second = 0;
            }
        }
    }

    public enum RunType {
        NEW,
        SAVE
    }

    public enum Difficulty {
        NORMAL,
        HARD,
        COFFIN
    }

    public static class ScoreHandle {
        public int score = 0;
        public int normal = 0;
        public int death = 0;
        public int pSkill = 0;
        public int alive = 0;
        public boolean more99 = false;
        public boolean devil = false;
        public HashMap<Integer, Integer> elite = new HashMap<>();
        public HashMap<Integer, Integer> boss = new HashMap<>();

        public ScoreHandle() {
            for (int i = 1; i <= 4; i++) {
                elite.put(i, 0);
                boss.put(i, 0);
            }
        }

        public void calculateScore() {
            score = 0;

            score += normal * 2;
            for (int i = 1; i <= 4; i++) {
                score += elite.get(i) * 10 * i;
                score += elite.get(i) * 50;
            }

            if (gold >= 2000) score += 75;
            else if (gold >= 1000) score += 50;
            else if (gold >= 500) score += 25;

            if (death > 0) score += death * 10;
            else score += 125;

            for (AbstractPlayer p : players) {
                if (p.isAlive()) alive++;
                for (AbstractSkill s : p.deck) {
                    if (s.id.equals("Strike") || s.id.equals("Barrier")) pSkill++;
                }
            }

            score += pSkill * 25;

            if (alive < 4) score += alive * 50;
            else score += 250;

            if (more99) score += 25;
            if (devil) score += 25;

            if (minute < 30) score += 75;
            else if (minute < 45) score += 50;
            else if (minute < 60) score += 25;

            if (diff == Difficulty.HARD) score *= 1.5f;
            else if (diff == Difficulty.COFFIN) score *= 2;
        }
    }
}
