package com.fastcat.labyrintale.players;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractTalent;

public class TestPlayer extends AbstractPlayer {

    private static final String ID = "TestPlayer";
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final int ATTACK = 75;
    private static final int SPELL = 65;
    private static final int HEALTH = 100;

    public TestPlayer() {
        super(ID, CLASS, ATTACK, SPELL, HEALTH);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        return null;
    }

    @Override
    public Array<AbstractTalent> getStartingSkill() {
        return null;
    }
}
