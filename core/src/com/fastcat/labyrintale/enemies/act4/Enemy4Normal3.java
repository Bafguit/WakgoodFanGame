package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy4Normal3 extends AbstractEnemy {

    private static final String ID = "Enemy4Normal3";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 142;

    public Enemy4Normal3() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 2;
        stat.critical = 5;
        stat.debuRes = 30;
        stat.neutRes = 20;
        stat.moveRes = 5;
    }

    @Override
    public void preBattle() {
        applyStatus(new AttackStatus(8), this, 5, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new RestrictE(this).upgrade();
        temp.add(s);
        temp.add(new AttackLowE(this));
        AbstractSkill s2 = new DecayE(this);
        s2.upgrade();
        temp.add(s2);
        temp.add(new AttackLowE(this));
        temp.add(new GrowE(this));
        return temp;
    }
}
