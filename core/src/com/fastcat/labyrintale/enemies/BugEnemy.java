package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;

public class BugEnemy extends AbstractEnemy {

    private static final String ID = "Bug";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 10;

    public BugEnemy() {
        super(ID, TYPE, HEALTH);
        stat.neutRes = 100;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new BarrierE(this));
        return temp;
    }
}
