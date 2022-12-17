package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AmbushE;

public class Enemy4Weak8 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak8";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 23;

    public Enemy4Weak8() {
        super(ID, TYPE, HEALTH);
        stat.speed = 3;
        stat.attack = 2;
        stat.critical = 20;
        stat.debuRes = 5;
        stat.neutRes = 10;
        stat.moveRes = 15;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AmbushE(this));
        return temp;
    }
}
