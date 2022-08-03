package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.InduceE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.skills.enemy.ThrowE;

public class Enemy2Weak3 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak3";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 42;

    public Enemy2Weak3() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        //TODO 다른 패턴으로 변경
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new ThrowE(this));
        return temp;
    }
}
