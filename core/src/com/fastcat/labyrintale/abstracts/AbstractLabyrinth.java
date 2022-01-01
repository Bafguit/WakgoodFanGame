package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.advisors.TestAdvisor;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.players.*;
import com.fastcat.labyrintale.screens.dead.DeadScreen;

import java.util.ArrayList;
import java.util.HashMap;

public class AbstractLabyrinth {

    public static Array<AbstractTalent> talentTree;
    public static HashMap<AbstractPlayer, Array<AbstractSkill>> skillList;

    public static RandomXS128 publicRandom;
    public static ActionHandler actionHandler;
    public static AbstractFloor currentFloor;
    public static AbstractPlayer[] players;
    public static AbstractAdvisor advisor;

    public AbstractLabyrinth() {
        publicRandom = new RandomXS128();
        actionHandler = new ActionHandler();
        currentFloor = new AbstractFloor();
        players = new AbstractPlayer[4];
        for(int i = 0; i < 4; i++) {
            players[i] = getPlayerInstance(Labyrintale.charSelectScreen.chars[i].selected);
        }
        advisor = getAdvisorInstance(Labyrintale.advisorSelectScreen.advisor.selected);
    }

    public void update() {
        if(!players[0].isAlive() && !players[1].isAlive() && !players[2].isAlive() && !players[3].isAlive()) {
            Labyrintale.fadeOutAndChangeScreen(new DeadScreen(DeadScreen.ScreenType.DEAD), 2.0f);
        }
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
}
