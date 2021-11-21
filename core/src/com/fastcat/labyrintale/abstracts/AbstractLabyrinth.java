package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.players.TestPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class AbstractLabyrinth {

    public static Array<AbstractTalent> talentTree;
    public static HashMap<AbstractPlayer, Array<AbstractSkill>> skillList;

    public static RandomXS128 publicRandom;
    public static ActionHandler actionHandler;
    public static AbstractPlayer player; //삭제할 예정
    public static AbstractPlayer[] players = new AbstractPlayer[4];
    public static AbstractFloor currentFloor;

    public AbstractLabyrinth() {
        publicRandom = new RandomXS128();
        actionHandler = new ActionHandler();
        currentFloor = new AbstractFloor();
        for(int i = 0; i < 4; i++) {
            players[i] = getPlayerInstance(Labyrintale.charSelectScreen.chars[i].selected);
        }
    }

    private static AbstractPlayer getPlayerInstance(AbstractPlayer.PlayerClass cls) {
        //TODO Code here
        return new TestPlayer();
    }
}
