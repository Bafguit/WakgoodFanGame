package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AmbushE;
import com.fastcat.labyrintale.skills.enemy.CorrosiveE;

public class Enemy2Weak10 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak10";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 15;

    public Enemy2Weak10() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 5;
        stat.debuRes = 5;
        stat.neutRes = 5;
        stat.moveRes = 25;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AmbushE(this));
        temp.add(new CorrosiveE(this));
        return temp;
    }
}
