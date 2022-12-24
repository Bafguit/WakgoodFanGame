package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AmbushE;

public class Enemy4Weak5 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak5";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 30;

    public Enemy4Weak5() {
        super(ID, TYPE, HEALTH);
        stat.speed = 1;
        stat.critical = 25;
        stat.debuRes = 5;
        stat.neutRes = 25;
        stat.moveRes = 40;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AmbushE(this).upgrade().upgrade().upgrade());
        return temp;
    }
}
