package com.fastcat.labyrintale.players;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractTalent;

public class TestPlayer extends AbstractPlayer {

    private static final String ID = "TestPlayer";
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final int HEALTH = 100;

    public TestPlayer() {
        super(ID, CLASS, HEALTH);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        return null;
    }
}
