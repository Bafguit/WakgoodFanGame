package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.advisors.TestAdvisor;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.players.*;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.HashMap;

public class AbstractLabyrinth {

    public static Array<AbstractTalent> talentTree;
    public static HashMap<AbstractPlayer, Array<AbstractSkill>> skillList;

    public static long seed0;
    public static long seed1;
    public static String seed;
    public static RandomXS128 publicRandom;
    public static RandomXS128 skillRandom;
    public static RandomXS128 relicRandom;
    public static RandomXS128 mapRandom;
    public static RandomXS128 monsterRandom;
    public static RandomXS128 eventRandom;
    public static RandomXS128 shopRandom;
    public static ActionHandler actionHandler;
    public static AbstractFloor currentFloor;
    public static AbstractPlayer[] players;
    public static AbstractAdvisor advisor;
    public static ControlPanel cPanel;
    public static int gold = 0;
    public static int removePrice = 50;

    public AbstractLabyrinth() {
        this(RunType.NEW);
    }

    public AbstractLabyrinth(RunType type) {
        actionHandler = new ActionHandler();
        players = new AbstractPlayer[4];
        if(type == RunType.SAVE) {
            seed = "Put Save Here";
            //여기에 세이브 입력
        } else if(type == RunType.CUSTOM) {
            seed = "Put Custom Here";
            //여기에 커스텀 입력
        } else {
            seed = generateRandomSeed();
            long seedLong = seedToLong(seed);
            publicRandom = new RandomXS128(seedLong);
            skillRandom = new RandomXS128(seedLong);
            relicRandom = new RandomXS128(seedLong);
            mapRandom = new RandomXS128(seedLong);
            monsterRandom = new RandomXS128(seedLong);
            eventRandom = new RandomXS128(seedLong);
            shopRandom = new RandomXS128(seedLong);
            currentFloor = new AbstractFloor();
            for (int i = 0; i < 4; i++) {
                players[i] = getPlayerInstance(Labyrintale.charSelectScreen.chars[i].selected);
            }
            advisor = getAdvisorInstance(Labyrintale.advisorSelectScreen.advisor.selected);
        }
        cPanel = new ControlPanel();
    }

    public void update() {

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

    public static long seedToLong(String s) {
        char[] ca = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : ca) {
            sb.append((int)c);
        }
        return Long.parseLong(sb.toString());
    }

    private static AbstractPlayer getPlayerInstance(AbstractPlayer.PlayerClass cls) {
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

    private static AbstractAdvisor getAdvisorInstance(AbstractAdvisor.AdvisorClass cls) {
        switch (cls) {
            default:
                return new TestAdvisor();
        }
    }

    public static int getRemovePrice(boolean isNormal) {
        int temp = removePrice;
        if(isNormal) removePrice += 25;
        return temp;
    }

    public enum RunType {
        NEW, CUSTOM, SAVE
    }
}
