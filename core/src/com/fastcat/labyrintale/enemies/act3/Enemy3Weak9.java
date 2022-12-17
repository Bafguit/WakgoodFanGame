package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AmbushE;

public class Enemy3Weak9 extends AbstractEnemy {

    private static final String ID = "Enemy3Weak9";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 20;

    public Enemy3Weak9() {
        super(ID, TYPE, HEALTH);
        stat.speed = 1;
        stat.attack = 1;
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
