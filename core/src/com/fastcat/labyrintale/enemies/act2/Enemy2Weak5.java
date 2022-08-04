package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.CounterE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.HostStatus;
import com.fastcat.labyrintale.status.LureStatus;

public class Enemy2Weak5 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak5";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 26;

    public Enemy2Weak5() {
        super(ID, TYPE, HEALTH);
    }

    @Override
    public void preBattle() {
        applyStatus(new HostStatus(2), 1, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new StrikeE(this);
        s1.upgrade();
        s1.upgrade();
        temp.add(s1);
        AbstractSkill s2 = new StrikeE(this);
        s2.upgrade();
        s2.upgrade();
        temp.add(s2);
        AbstractSkill s3 = new FuryE(this);
        s3.upgrade();
        s3.upgrade();
        temp.add(s3);
        return temp;
    }
}
