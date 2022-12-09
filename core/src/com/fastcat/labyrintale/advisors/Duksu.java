package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.*;

public class Duksu extends AbstractItem {

    private static final String ID = "duksu";
    private static final ItemRarity RARITY = ItemRarity.ADVISOR;

    public Duksu() {
        super(ID, null, RARITY);
    }

    public void atBattleStart() {
        for (int i = 0; i < 4; i++) {
            AbstractPlayer p = AbstractLabyrinth.players[i];
            AbstractSkill s = p.hand[AbstractLabyrinth.publicRandom.random(0, 2)];
            s.upgrade();
        }
    }
}
