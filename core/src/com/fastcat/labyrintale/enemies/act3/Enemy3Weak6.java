package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.ThrowE;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy3Weak6 extends AbstractEnemy {

    private static final String ID = "Enemy3Weak6";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 46;

    public Enemy3Weak6() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 1;
        stat.critical = 10;
        stat.debuRes = 10;
        stat.neutRes = 5;
        stat.moveRes = 40;
    }

    @Override
    public void preBattle() {
        applyStatus(new AttackStatus(2), this, 2, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new ThrowE(this));
        return temp;
    }
}
