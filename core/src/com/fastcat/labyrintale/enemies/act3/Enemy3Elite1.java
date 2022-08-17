package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy3Elite1 extends AbstractEnemy {

    private static final String ID = "Enemy3Elite1";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 67;

    public Enemy3Elite1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new HinderE(this);
        for(int i = 0; i < 3; i++) {
            s.upgrade();
        }
        temp.add(s);
        AbstractSkill s2 = new CorrosE(this);
        for(int i = 0; i < 1; i++) {
            s2.upgrade();
        }
        AbstractSkill s1 = new HinderE(this);
        for(int i = 0; i < 3; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s3 = new EncourageE(this);
        s3.upgrade();
        temp.add(s3);
        return temp;
    }
}
