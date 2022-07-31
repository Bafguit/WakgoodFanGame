package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.ThrowE;

public class Enemy2Weak4 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak4";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 42;

    public Enemy2Weak4() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new ThrowE(this));
        return temp;
    }
}
