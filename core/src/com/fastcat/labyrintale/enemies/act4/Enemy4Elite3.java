package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ArmourStatus;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy4Elite3 extends AbstractEnemy {

    private static final String ID = "Enemy4Elite3";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 107;

    public Enemy4Elite3() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public void preBattle() {
        applyStatus(new PunishStatus(2), 2, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new FourE(this);
        s.upgrade();
        temp.add(s);
        AbstractSkill s2 = new DecayE(this);
        s2.upgrade();
        temp.add(s2);
        temp.add(new AllTwoE(this));
        temp.add(new FuryE(this));
        return temp;
    }
}