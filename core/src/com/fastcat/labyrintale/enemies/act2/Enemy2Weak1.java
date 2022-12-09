package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.CounterE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.LureStatus;

public class Enemy2Weak1 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak1";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 37;

    public Enemy2Weak1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 0;
        stat.critical = 5;
        stat.debuRes = 10;
        stat.neutRes = 5;
        stat.moveRes = 30;
    }

    @Override
    public void preBattle() {
        applyStatus(new LureStatus(), this, 1, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new StrikeE(this);
        for (int i = 0; i < 3; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        temp.add(new CounterE(this));
        return temp;
    }
}
