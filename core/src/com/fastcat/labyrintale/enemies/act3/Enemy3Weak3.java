package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AttackLowE;
import com.fastcat.labyrintale.skills.enemy.UnstoppableE;

public class Enemy3Weak3 extends AbstractEnemy {

    private static final String ID = "Enemy3Weak3";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 82;

    public Enemy3Weak3() {
        super(ID, TYPE, HEALTH);
        stat.speed = 1;
        stat.critical = 20;
        stat.debuRes = 15;
        stat.neutRes = 10;
        stat.moveRes = 5;
    }

    @Override
    public void preBattle() {}

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new UnstoppableE(this);
        for (int i = 0; i < 2; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s4 = new AttackLowE(this);
        for (int i = 0; i < 5; i++) {
            s4.upgrade();
        }
        temp.add(s4);
        return temp;
    }
}
