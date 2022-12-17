package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AutoAttackE;

public class Enemy3Normal5 extends AbstractEnemy {

    private static final String ID = "Enemy3Normal5";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 40;

    public Enemy3Normal5() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 10;
        stat.debuRes = 15;
        stat.neutRes = 5;
        stat.moveRes = 30;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AutoAttackE(this));
        return temp;
    }
}
