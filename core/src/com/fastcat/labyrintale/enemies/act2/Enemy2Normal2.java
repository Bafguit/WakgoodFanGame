package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ArmourStatus;
import com.fastcat.labyrintale.status.SpikeStatus;

public class Enemy2Normal2 extends AbstractEnemy {

    private static final String ID = "Enemy2Normal2";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 30;

    public Enemy2Normal2() {
        super(ID, TYPE, HEALTH);
    }

    @Override
    public void preBattle() {
        applyStatus(new SpikeStatus(1), 1, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new SpikeE(this));
        temp.add(new SpikeE(this));
        AbstractSkill s1 = new BarrierE(this);
        for(int i = 0; i < 3; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s2 = new StrikeE(this);
        for(int i = 0; i < 2; i++) {
            s2.upgrade();
        }
        temp.add(s2);
        return temp;
    }
}
