package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AmbushE;
import com.fastcat.labyrintale.skills.enemy.CorrosiveE;

public class Enemy2Weak6 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak6";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 21;

    public Enemy2Weak6() {
        super(ID, TYPE, HEALTH);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AmbushE(this));
        return temp;
    }
}
