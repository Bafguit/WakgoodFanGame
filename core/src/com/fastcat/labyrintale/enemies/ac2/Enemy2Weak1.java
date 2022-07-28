package com.fastcat.labyrintale.enemies.ac2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class Enemy2Weak1 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak1";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 32;

    public Enemy2Weak1() {
        super(ID, TYPE, HEALTH);
    }

    @Override
    public void preBattle() {

    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new StrikeE(this));
        temp.add(new BarrierE(this));
        temp.add(new FuryE(this));
        return temp;
    }
}
