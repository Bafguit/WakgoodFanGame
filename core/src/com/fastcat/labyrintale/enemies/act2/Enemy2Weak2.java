package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class Enemy2Weak2 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak2";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 44;

    public Enemy2Weak2() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new InduceE(this));
        AbstractSkill s1 = new StrikeE(this);
        for(int i = 0; i < 4; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        temp.add(new GrowE(this));
        return temp;
    }
}
